<template>
  <a-card :bordered="false">
    <!-- 查询区域 -->
    <div class="table-page-search-wrapper">
      <a-form layout="inline" @keyup.enter.native="searchQuery">
        <a-row :gutter="24">
        </a-row>
      </a-form>
    </div>
    <!-- 查询区域-END -->

    <!-- 操作按钮区域 -->
    <div class="table-operator">
      <a-button @click="handleAdd" type="primary" icon="plus">新增</a-button>
      <a-button type="primary" icon="download" @click="handleExportXls('返点比例')">导出</a-button>
      <a-upload name="file" :showUploadList="false" :multiple="false" :headers="tokenHeader" :action="importExcelUrl" @change="handleImportExcel">
        <a-button type="primary" icon="import">导入</a-button>
      </a-upload>
      <!-- 高级查询区域 -->
      <j-super-query :fieldList="superFieldList" ref="superQueryModal" @handleSuperQuery="handleSuperQuery"></j-super-query>
      <a-dropdown v-if="selectedRowKeys.length > 0">
        <a-menu slot="overlay">
          <a-menu-item key="1" @click="batchDel"><a-icon type="delete"/>删除</a-menu-item>
        </a-menu>
        <a-button style="margin-left: 8px"> 批量操作 <a-icon type="down" /></a-button>
      </a-dropdown>
    </div>

    <!-- table区域-begin -->
    <div>
      <div class="ant-alert ant-alert-info" style="margin-bottom: 16px;">
        <i class="anticon anticon-info-circle ant-alert-icon"></i> 已选择 <a style="font-weight: 600">{{ selectedRowKeys.length }}</a>项
        <a style="margin-left: 24px" @click="onClearSelected">清空</a>
      </div>

      <a-table
        ref="table"
        size="middle"
        :scroll="{x:true}"
        bordered
        rowKey="id"
        :columns="columns"
        :dataSource="dataSource"
        :pagination="ipagination"
        :loading="loading"
        :rowSelection="{selectedRowKeys: selectedRowKeys, onChange: onSelectChange}"
        class="j-table-force-nowrap"
        @change="handleTableChange">

        <template slot="htmlSlot" slot-scope="text">
          <div v-html="text"></div>
        </template>
        <template slot="imgSlot" slot-scope="text">
          <span v-if="!text" style="font-size: 12px;font-style: italic;">无图片</span>
          <img v-else :src="getImgView(text)" height="25px" alt="" style="max-width:80px;font-size: 12px;font-style: italic;"/>
        </template>
        <template slot="fileSlot" slot-scope="text">
          <span v-if="!text" style="font-size: 12px;font-style: italic;">无文件</span>
          <a-button
            v-else
            :ghost="true"
            type="primary"
            icon="download"
            size="small"
            @click="downloadFile(text)">
            下载
          </a-button>
        </template>

        <span slot="action" slot-scope="text, record">
          <a @click="handleEdit(record)">编辑</a>

          <a-divider type="vertical" />
          <a-dropdown>
            <a class="ant-dropdown-link">更多 <a-icon type="down" /></a>
            <a-menu slot="overlay">
              <a-menu-item>
                <a @click="handleDetail(record)">详情</a>
              </a-menu-item>
              <a-menu-item>
                <a-popconfirm title="确定删除吗?" @confirm="() => handleDelete(record.id)">
                  <a>删除</a>
                </a-popconfirm>
              </a-menu-item>
            </a-menu>
          </a-dropdown>
        </span>

      </a-table>
    </div>

    <insurance-rebate-ratio-modal ref="modalForm" @ok="modalFormOk"></insurance-rebate-ratio-modal>
  </a-card>
</template>

<script>

  import '@/assets/less/TableExpand.less'
  import { mixinDevice } from '@/utils/mixin'
  import { JeecgListMixin } from '@/mixins/JeecgListMixin'
  import InsuranceRebateRatioModal from './modules/InsuranceRebateRatioModal'
  import {filterMultiDictText} from '@/components/dict/JDictSelectUtil'

  export default {
    name: 'InsuranceRebateRatioList',
    mixins:[JeecgListMixin, mixinDevice],
    components: {
      InsuranceRebateRatioModal
    },
    data () {
      return {
        description: '返点比例管理页面',
        // 表头
        columns: [
          {
            title: '#',
            dataIndex: '',
            key:'rowIndex',
            width:60,
            align:"center",
            customRender:function (t,r,index) {
              return parseInt(index)+1;
            }
          },
          {
            title:'返点类型',
            align:"center",
            dataIndex: 'rebateRatioType_dictText'
          },
          {
            title:'是否过户',
            align:"center",
            dataIndex: 'isTransfer_dictText'
          },
          {
            title:'续保类型',
            align:"center",
            dataIndex: 'renewalType_dictText'
          },
          {
            title:'使用性质',
            align:"center",
            dataIndex: 'usageType_dictText'
          },
          {
            title:'三责保额档',
            align:"center",
            dataIndex: 'thirdPartyInsured'
          },
          {
            title:'车损险保额档',
            align:"center",
            dataIndex: 'carDamageInsured'
          },
          {
            title:'司机责任险保额档',
            align:"center",
            dataIndex: 'driverLiabilityInsured'
          },
          {
            title:'乘客责任险保额档',
            align:"center",
            dataIndex: 'passengerLiability'
          },
          {
            title:'返点比例',
            align:"center",
            dataIndex: 'rebateRatio'
          },
          {
            title:'奖金',
            align:"center",
            dataIndex: 'bonus'
          },
          {
            title: '操作',
            dataIndex: 'action',
            align:"center",
            fixed:"right",
            width:147,
            scopedSlots: { customRender: 'action' }
          }
        ],
        url: {
          list: "/rebate/insuranceRebateRatio/list",
          delete: "/rebate/insuranceRebateRatio/delete",
          deleteBatch: "/rebate/insuranceRebateRatio/deleteBatch",
          exportXlsUrl: "/rebate/insuranceRebateRatio/exportXls",
          importExcelUrl: "rebate/insuranceRebateRatio/importExcel",
          
        },
        dictOptions:{},
        superFieldList:[],
      }
    },
    created() {
    this.getSuperFieldList();
    },
    computed: {
      importExcelUrl: function(){
        return `${window._CONFIG['domianURL']}/${this.url.importExcelUrl}`;
      },
    },
    methods: {
      initDictConfig(){
      },
      getSuperFieldList(){
        let fieldList=[];
        fieldList.push({type:'int',value:'rebateRatioType',text:'返点类型',dictCode:'rebate_ratio_type'})
        fieldList.push({type:'string',value:'isTransfer',text:'是否过户',dictCode:'is_transfer'})
        fieldList.push({type:'string',value:'renewalType',text:'续保类型',dictCode:'renewal_symbol'})
        fieldList.push({type:'string',value:'usageType',text:'使用性质',dictCode:'insurance_usage,usage_name,usage_type'})
        fieldList.push({type:'string',value:'thirdPartyInsured',text:'三责保额档',dictCode:''})
        fieldList.push({type:'string',value:'carDamageInsured',text:'车损险保额档',dictCode:''})
        fieldList.push({type:'string',value:'driverLiabilityInsured',text:'司机责任险保额档',dictCode:''})
        fieldList.push({type:'string',value:'passengerLiability',text:'乘客责任险保额档',dictCode:''})
        fieldList.push({type:'double',value:'rebateRatio',text:'返点比例',dictCode:''})
        fieldList.push({type:'double',value:'bonus',text:'奖金',dictCode:''})
        this.superFieldList = fieldList
      }
    }
  }
</script>
<style scoped>
  @import '~@assets/less/common.less';
</style>