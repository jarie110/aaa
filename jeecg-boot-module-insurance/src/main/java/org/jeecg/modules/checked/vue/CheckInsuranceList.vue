<template>
  <a-card :bordered="false">
    <!-- 查询区域 -->
    <div class="table-page-search-wrapper">
      <a-form layout="inline" @keyup.enter.native="searchQuery">
        <a-row :gutter="24">
          <a-col :xl="10" :lg="11" :md="12" :sm="24">
            <a-form-item label="录入日期">
              <j-date placeholder="请选择开始日期" class="query-group-cust" v-model="queryParam.inputInsuranceDate_begin"></j-date>
              <span class="query-group-split-cust"></span>
              <j-date placeholder="请选择结束日期" class="query-group-cust" v-model="queryParam.inputInsuranceDate_end"></j-date>
            </a-form-item>
          </a-col>
          <a-col :xl="10" :lg="11" :md="12" :sm="24">
            <a-form-item label="出单日期">
              <j-date placeholder="请选择开始日期" class="query-group-cust" v-model="queryParam.insuranceDate_begin"></j-date>
              <span class="query-group-split-cust"></span>
              <j-date placeholder="请选择结束日期" class="query-group-cust" v-model="queryParam.insuranceDate_end"></j-date>
            </a-form-item>
          </a-col>
          <template v-if="toggleSearchStatus">
            <a-col :xl="6" :lg="7" :md="8" :sm="24">
              <a-form-item label="所属团队">
                <a-input placeholder="请输入所属团队" v-model="queryParam.insuranceTeam"></a-input>
              </a-form-item>
            </a-col>
            <a-col :xl="6" :lg="7" :md="8" :sm="24">
              <a-form-item label="客户名称">
                <a-input placeholder="请输入客户名称" v-model="queryParam.customerName"></a-input>
              </a-form-item>
            </a-col>
            <a-col :xl="6" :lg="7" :md="8" :sm="24">
              <a-form-item label="车架号">
                <a-input placeholder="请输入车架号" v-model="queryParam.vehicleIdentity"></a-input>
              </a-form-item>
            </a-col>
            <a-col :xl="6" :lg="7" :md="8" :sm="24">
              <a-form-item label="交强险保单号">
                <a-input placeholder="请输入交强险保单号" v-model="queryParam.compulsoryInsurCode"></a-input>
              </a-form-item>
            </a-col>
            <a-col :xl="6" :lg="7" :md="8" :sm="24">
              <a-form-item label="商业险保单号">
                <a-input placeholder="请输入商业险保单号" v-model="queryParam.commercialInsurCode"></a-input>
              </a-form-item>
            </a-col>
            <a-col :xl="6" :lg="7" :md="8" :sm="24">
              <a-form-item label="渠道名称">
                <a-input placeholder="请输入渠道名称" v-model="queryParam.distributionChannelId"></a-input>
              </a-form-item>
            </a-col>
            <a-col :xl="6" :lg="7" :md="8" :sm="24">
              <a-form-item label="是否返点">
                <a-input placeholder="请输入是否返点" v-model="queryParam.isRebate"></a-input>
              </a-form-item>
            </a-col>
            <a-col :xl="6" :lg="7" :md="8" :sm="24">
              <a-form-item label="返点收取方式">
                <a-input placeholder="请输入返点收取方式" v-model="queryParam.rebateWay"></a-input>
              </a-form-item>
            </a-col>
          </template>
          <a-col :xl="6" :lg="7" :md="8" :sm="24">
            <span style="float: left;overflow: hidden;" class="table-page-search-submitButtons">
              <a-button type="primary" @click="searchQuery" icon="search">查询</a-button>
              <a-button type="primary" @click="searchReset" icon="reload" style="margin-left: 8px">重置</a-button>
              <a @click="handleToggleSearch" style="margin-left: 8px">
                {{ toggleSearchStatus ? '收起' : '展开' }}
                <a-icon :type="toggleSearchStatus ? 'up' : 'down'"/>
              </a>
            </span>
          </a-col>
        </a-row>
      </a-form>
    </div>
    <!-- 查询区域-END -->

    <!-- 操作按钮区域 -->
    <div class="table-operator">
      <a-button @click="handleAdd" type="primary" icon="plus">新增</a-button>
      <a-button type="primary" icon="download" @click="handleExportXls('核对的保单')">导出</a-button>
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

    <check-insurance-modal ref="modalForm" @ok="modalFormOk"></check-insurance-modal>
  </a-card>
</template>

<script>

  import '@/assets/less/TableExpand.less'
  import { mixinDevice } from '@/utils/mixin'
  import { JeecgListMixin } from '@/mixins/JeecgListMixin'
  import CheckInsuranceModal from './modules/CheckInsuranceModal'

  export default {
    name: 'CheckInsuranceList',
    mixins:[JeecgListMixin, mixinDevice],
    components: {
      CheckInsuranceModal
    },
    data () {
      return {
        description: '核对的保单管理页面',
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
            title:'保单编号',
            align:"center",
            dataIndex: 'insureNum'
          },
          {
            title:'录入日期',
            align:"center",
            dataIndex: 'inputInsuranceDate',
            customRender:function (text) {
              return !text?"":(text.length>10?text.substr(0,10):text)
            }
          },
          {
            title:'比对日期',
            align:"center",
            dataIndex: 'checkDate',
            customRender:function (text) {
              return !text?"":(text.length>10?text.substr(0,10):text)
            }
          },
          {
            title:'出单日期',
            align:"center",
            dataIndex: 'insuranceDate',
            customRender:function (text) {
              return !text?"":(text.length>10?text.substr(0,10):text)
            }
          },
          {
            title:'出单员',
            align:"center",
            dataIndex: 'salesman'
          },
          {
            title:'所属团队',
            align:"center",
            dataIndex: 'insuranceTeam'
          },
          {
            title:'客户名称',
            align:"center",
            dataIndex: 'customerName'
          },
          {
            title:'车架号',
            align:"center",
            dataIndex: 'vehicleIdentity'
          },
          {
            title:'交强险保单号',
            align:"center",
            dataIndex: 'compulsoryInsurCode'
          },
          {
            title:'商业险保单号',
            align:"center",
            dataIndex: 'commercialInsurCode'
          },
          {
            title:'交强险保费',
            align:"center",
            dataIndex: 'compulsoryInsurFee'
          },
          {
            title:'保费总额',
            align:"center",
            dataIndex: 'insuranceTotalFee'
          },
          {
            title:'交强险手续费比例',
            align:"center",
            dataIndex: 'compulsoryInsuranceRebate'
          },
          {
            title:'商业险手续费比例',
            align:"center",
            dataIndex: 'commercialInsuranceRebate'
          },
          {
            title:'额外奖励金额',
            align:"center",
            dataIndex: 'bonus'
          },
          {
            title:'渠道名称',
            align:"center",
            dataIndex: 'distributionChannelId'
          },
          {
            title:'起保日期',
            align:"center",
            dataIndex: 'insureStartDate',
            customRender:function (text) {
              return !text?"":(text.length>10?text.substr(0,10):text)
            }
          },
          {
            title:'初登日期',
            align:"center",
            dataIndex: 'registerDate',
            customRender:function (text) {
              return !text?"":(text.length>10?text.substr(0,10):text)
            }
          },
          {
            title:'签单手续费',
            align:"center",
            dataIndex: 'signServiceHarge'
          },
          {
            title:'新续保标志',
            align:"center",
            dataIndex: 'renewalType'
          },
          {
            title:'使用性质',
            align:"center",
            dataIndex: 'carUsageType'
          },
          {
            title:'车辆种类',
            align:"center",
            dataIndex: 'vehiclesType'
          },
          {
            title:'是否录入过户标志',
            align:"center",
            dataIndex: 'isTransfer'
          },
          {
            title:'三责保额',
            align:"center",
            dataIndex: 'thirdPartyInsured'
          },
          {
            title:'车损险保额',
            align:"center",
            dataIndex: 'carDamageInsured'
          },
          {
            title:'承保险别名称',
            align:"center",
            dataIndex: 'insuranceAlias'
          },
          {
            title:'座位',
            align:"center",
            dataIndex: 'seatsNum'
          },
          {
            title:'司机责任险保额',
            align:"center",
            dataIndex: 'driverLiabilityInsure'
          },
          {
            title:'乘客责任险保额',
            align:"center",
            dataIndex: 'passengerLiability'
          },
          {
            title:'手续费总额',
            align:"center",
            dataIndex: 'totalServiceFee'
          },
          {
            title:'是否返点',
            align:"center",
            dataIndex: 'isRebate_dictText'
          },
          {
            title:'返点支付时间',
            align:"center",
            dataIndex: 'rebatePaymentDate',
            customRender:function (text) {
              return !text?"":(text.length>10?text.substr(0,10):text)
            }
          },
          {
            title:'返点收取方式',
            align:"center",
            dataIndex: 'rebateWay'
          },
          {
            title:'车牌号',
            align:"center",
            dataIndex: 'vehicleLicense'
          },
          {
            title:'商业险保费',
            align:"center",
            dataIndex: 'commercialInsurFee'
          },
          {
            title:'车船税',
            align:"center",
            dataIndex: 'vehicleVesselTax'
          },
          {
            title:'备注',
            align:"center",
            dataIndex: 'remarks'
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
          list: "/checked/checkInsurance/list",
          delete: "/checked/checkInsurance/delete",
          deleteBatch: "/checked/checkInsurance/deleteBatch",
          exportXlsUrl: "/checked/checkInsurance/exportXls",
          importExcelUrl: "checked/checkInsurance/importExcel",
          
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
        fieldList.push({type:'string',value:'insureNum',text:'保单编号',dictCode:''})
        fieldList.push({type:'date',value:'inputInsuranceDate',text:'录入日期'})
        fieldList.push({type:'date',value:'checkDate',text:'比对日期'})
        fieldList.push({type:'date',value:'insuranceDate',text:'出单日期'})
        fieldList.push({type:'string',value:'salesman',text:'出单员',dictCode:''})
        fieldList.push({type:'string',value:'insuranceTeam',text:'所属团队',dictCode:''})
        fieldList.push({type:'string',value:'customerName',text:'客户名称',dictCode:''})
        fieldList.push({type:'string',value:'vehicleIdentity',text:'车架号',dictCode:''})
        fieldList.push({type:'string',value:'compulsoryInsurCode',text:'交强险保单号',dictCode:''})
        fieldList.push({type:'string',value:'commercialInsurCode',text:'商业险保单号',dictCode:''})
        fieldList.push({type:'double',value:'compulsoryInsurFee',text:'交强险保费',dictCode:''})
        fieldList.push({type:'double',value:'insuranceTotalFee',text:'保费总额',dictCode:''})
        fieldList.push({type:'double',value:'compulsoryInsuranceRebate',text:'交强险手续费比例',dictCode:''})
        fieldList.push({type:'double',value:'commercialInsuranceRebate',text:'商业险手续费比例',dictCode:''})
        fieldList.push({type:'double',value:'bonus',text:'额外奖励金额',dictCode:''})
        fieldList.push({type:'string',value:'distributionChannelId',text:'渠道名称',dictCode:''})
        fieldList.push({type:'date',value:'insureStartDate',text:'起保日期'})
        fieldList.push({type:'date',value:'registerDate',text:'初登日期'})
        fieldList.push({type:'double',value:'signServiceHarge',text:'签单手续费',dictCode:''})
        fieldList.push({type:'string',value:'renewalType',text:'新续保标志',dictCode:''})
        fieldList.push({type:'string',value:'carUsageType',text:'使用性质',dictCode:''})
        fieldList.push({type:'string',value:'vehiclesType',text:'车辆种类',dictCode:''})
        fieldList.push({type:'string',value:'isTransfer',text:'是否录入过户标志',dictCode:''})
        fieldList.push({type:'int',value:'thirdPartyInsured',text:'三责保额',dictCode:''})
        fieldList.push({type:'int',value:'carDamageInsured',text:'车损险保额',dictCode:''})
        fieldList.push({type:'string',value:'insuranceAlias',text:'承保险别名称',dictCode:''})
        fieldList.push({type:'int',value:'seatsNum',text:'座位',dictCode:''})
        fieldList.push({type:'int',value:'driverLiabilityInsure',text:'司机责任险保额',dictCode:''})
        fieldList.push({type:'int',value:'passengerLiability',text:'乘客责任险保额',dictCode:''})
        fieldList.push({type:'double',value:'totalServiceFee',text:'手续费总额',dictCode:''})
        fieldList.push({type:'string',value:'isRebate',text:'是否返点',dictCode:''})
        fieldList.push({type:'date',value:'rebatePaymentDate',text:'返点支付时间'})
        fieldList.push({type:'int',value:'rebateWay',text:'返点收取方式',dictCode:''})
        fieldList.push({type:'string',value:'vehicleLicense',text:'车牌号',dictCode:''})
        fieldList.push({type:'double',value:'commercialInsurFee',text:'商业险保费',dictCode:''})
        fieldList.push({type:'double',value:'vehicleVesselTax',text:'车船税',dictCode:''})
        fieldList.push({type:'string',value:'remarks',text:'备注',dictCode:''})
        this.superFieldList = fieldList
      }
    }
  }
</script>
<style scoped>
  @import '~@assets/less/common.less';
</style>