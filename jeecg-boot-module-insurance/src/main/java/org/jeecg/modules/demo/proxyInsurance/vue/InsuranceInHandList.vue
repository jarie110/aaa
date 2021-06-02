<template>
  <a-card :bordered="false">
    <!-- 查询区域 -->
    <div class="table-page-search-wrapper">
      <a-form layout="inline" @keyup.enter.native="searchQuery">
        <a-row :gutter="24">
          <a-col :xl="10" :lg="11" :md="12" :sm="24">
            <a-form-item label="出单日期">
              <j-date placeholder="请选择开始日期" class="query-group-cust" v-model="queryParam.insuranceDate_begin"></j-date>
              <span class="query-group-split-cust"></span>
              <j-date placeholder="请选择结束日期" class="query-group-cust" v-model="queryParam.insuranceDate_end"></j-date>
            </a-form-item>
          </a-col>
          <a-col :xl="6" :lg="7" :md="8" :sm="24">
            <a-form-item label="车牌号">
              <a-input placeholder="请输入车牌号" v-model="queryParam.vehicleLicense"></a-input>
            </a-form-item>
          </a-col>
          <template v-if="toggleSearchStatus">
            <a-col :xl="6" :lg="7" :md="8" :sm="24">
              <a-form-item label="客户名称">
                <a-input placeholder="请输入客户名称" v-model="queryParam.customer"></a-input>
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
                <j-dict-select-tag placeholder="请选择渠道名称" v-model="queryParam.distributionChannelId" dictCode="distribution_channel,channel_name,channel_type"/>
              </a-form-item>
            </a-col>
            <a-col :xl="6" :lg="7" :md="8" :sm="24">
              <a-form-item label="所属团队">
                <j-dict-select-tag placeholder="请选择所属团队" v-model="queryParam.insuranceTeam" dictCode="insurance_team,team_name,team_code"/>
              </a-form-item>
            </a-col>
            <a-col :xl="6" :lg="7" :md="8" :sm="24">
              <a-form-item label="是否返佣">
                <j-dict-select-tag placeholder="请选择是否返佣" v-model="queryParam.isPayCommission" dictCode="is_paid_rebate"/>
              </a-form-item>
            </a-col>
            <a-col :xl="6" :lg="7" :md="8" :sm="24">
              <a-form-item label="是否比对">
                <j-dict-select-tag placeholder="请选择是否比对" v-model="queryParam.isChecked" dictCode="is_checked"/>
              </a-form-item>
            </a-col>
            <a-col :xl="6" :lg="7" :md="8" :sm="24">
              <a-form-item label="返点支付方式">
                <j-dict-select-tag placeholder="请选择返点支付方式" v-model="queryParam.paymentWay" dictCode="rebate_way"/>
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
      <a-button type="primary" icon="download" @click="handleExportXls('手输保单')">导出</a-button>
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

    <insurance-in-hand-modal ref="modalForm" @ok="modalFormOk"></insurance-in-hand-modal>
  </a-card>
</template>

<script>

  import '@/assets/less/TableExpand.less'
  import { mixinDevice } from '@/utils/mixin'
  import { JeecgListMixin } from '@/mixins/JeecgListMixin'
  import InsuranceInHandModal from './modules/InsuranceInHandModal'
  import {filterMultiDictText} from '@/components/dict/JDictSelectUtil'

  export default {
    name: 'InsuranceInHandList',
    mixins:[JeecgListMixin, mixinDevice],
    components: {
      InsuranceInHandModal
    },
    data () {
      return {
        description: '手输保单管理页面',
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
            title:'车牌号',
            align:"center",
            dataIndex: 'vehicleLicense'
          },
          {
            title:'客户名称',
            align:"center",
            dataIndex: 'customer'
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
            title:'交强险保费',
            align:"center",
            dataIndex: 'compulsoryInsurFee'
          },
          {
            title:'商业险保单号',
            align:"center",
            dataIndex: 'commercialInsurCode'
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
            title:'保费总额',
            align:"center",
            dataIndex: 'insuranceTotalFee'
          },
          {
            title:'手续费总额',
            align:"center",
            dataIndex: 'totalServiceFee'
          },
          {
            title:'新续保标志',
            align:"center",
            dataIndex: 'renewalType_dictText'
          },
          {
            title:'使用性质',
            align:"center",
            dataIndex: 'carUsageType_dictText'
          },
          {
            title:'渠道名称',
            align:"center",
            dataIndex: 'distributionChannelId_dictText'
          },
          {
            title:'备注',
            align:"center",
            dataIndex: 'remark'
          },
          {
            title:'所属团队',
            align:"center",
            dataIndex: 'insuranceTeam_dictText'
          },
          {
            title:'三者责任险保额',
            align:"center",
            dataIndex: 'thirdPartyInsured'
          },
          {
            title:'司机责任险保额',
            align:"center",
            dataIndex: 'driverLiabilityInsure'
          },
          {
            title:'车损险保额',
            align:"center",
            dataIndex: 'carDamageInsured'
          },
          {
            title:'乘客责任险保额',
            align:"center",
            dataIndex: 'passengerLiability'
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
            title:'是否过户',
            align:"center",
            dataIndex: 'isTransfer_dictText'
          },
          {
            title:'签单手续费',
            align:"center",
            dataIndex: 'signFee'
          },
          {
            title:'座位数',
            align:"center",
            dataIndex: 'seatsNum'
          },
          {
            title:'交强险返点比',
            align:"center",
            dataIndex: 'compulsoryInsuranceRebate'
          },
          {
            title:'商业险返点比',
            align:"center",
            dataIndex: 'commercialInsuranceRebate'
          },
          {
            title:'是否返佣',
            align:"center",
            dataIndex: 'isPayCommission_dictText'
          },
          {
            title:'是否比对',
            align:"center",
            dataIndex: 'isChecked_dictText'
          },
          {
            title:'座位保奖励金',
            align:"center",
            dataIndex: 'seatBonus'
          },
          {
            title:'返点支付方式',
            align:"center",
            dataIndex: 'paymentWay_dictText'
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
          list: "/proxyInsurance/insuranceInHand/list",
          delete: "/proxyInsurance/insuranceInHand/delete",
          deleteBatch: "/proxyInsurance/insuranceInHand/deleteBatch",
          exportXlsUrl: "/proxyInsurance/insuranceInHand/exportXls",
          importExcelUrl: "proxyInsurance/insuranceInHand/importExcel",
          
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
        fieldList.push({type:'date',value:'insuranceDate',text:'出单日期'})
        fieldList.push({type:'string',value:'salesman',text:'出单员',dictCode:''})
        fieldList.push({type:'string',value:'vehicleLicense',text:'车牌号',dictCode:''})
        fieldList.push({type:'string',value:'customer',text:'客户名称',dictCode:''})
        fieldList.push({type:'string',value:'vehicleIdentity',text:'车架号',dictCode:''})
        fieldList.push({type:'string',value:'compulsoryInsurCode',text:'交强险保单号',dictCode:''})
        fieldList.push({type:'double',value:'compulsoryInsurFee',text:'交强险保费',dictCode:''})
        fieldList.push({type:'string',value:'commercialInsurCode',text:'商业险保单号',dictCode:''})
        fieldList.push({type:'double',value:'commercialInsurFee',text:'商业险保费',dictCode:''})
        fieldList.push({type:'double',value:'vehicleVesselTax',text:'车船税',dictCode:''})
        fieldList.push({type:'double',value:'insuranceTotalFee',text:'保费总额',dictCode:''})
        fieldList.push({type:'double',value:'totalServiceFee',text:'手续费总额',dictCode:''})
        fieldList.push({type:'int',value:'renewalType',text:'新续保标志',dictCode:'renewal_symbol'})
        fieldList.push({type:'int',value:'carUsageType',text:'使用性质',dictCode:'insurance_usage,usage_name,usage_type'})
        fieldList.push({type:'int',value:'distributionChannelId',text:'渠道名称',dictCode:'distribution_channel,channel_name,channel_type'})
        fieldList.push({type:'string',value:'remark',text:'备注',dictCode:''})
        fieldList.push({type:'int',value:'insuranceTeam',text:'所属团队',dictCode:'insurance_team,team_name,team_code'})
        fieldList.push({type:'int',value:'thirdPartyInsured',text:'三者责任险保额',dictCode:''})
        fieldList.push({type:'int',value:'driverLiabilityInsure',text:'司机责任险保额',dictCode:''})
        fieldList.push({type:'int',value:'carDamageInsured',text:'车损险保额',dictCode:''})
        fieldList.push({type:'int',value:'passengerLiability',text:'乘客责任险保额',dictCode:''})
        fieldList.push({type:'date',value:'registerDate',text:'初登日期'})
        fieldList.push({type:'int',value:'isTransfer',text:'是否过户',dictCode:'is_transfer'})
        fieldList.push({type:'double',value:'signFee',text:'签单手续费',dictCode:''})
        fieldList.push({type:'int',value:'seatsNum',text:'座位数',dictCode:''})
        fieldList.push({type:'double',value:'compulsoryInsuranceRebate',text:'交强险返点比',dictCode:''})
        fieldList.push({type:'double',value:'commercialInsuranceRebate',text:'商业险返点比',dictCode:''})
        fieldList.push({type:'int',value:'isPayCommission',text:'是否返佣',dictCode:'is_paid_rebate'})
        fieldList.push({type:'int',value:'isChecked',text:'是否比对',dictCode:'is_checked'})
        fieldList.push({type:'double',value:'seatBonus',text:'座位保奖励金',dictCode:''})
        fieldList.push({type:'int',value:'paymentWay',text:'返点支付方式',dictCode:'rebate_way'})
        this.superFieldList = fieldList
      }
    }
  }
</script>
<style scoped>
  @import '~@assets/less/common.less';
</style>