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
      <a-button type="primary" icon="download" @click="handleExportXls('保险公司保单')">导出</a-button>
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

    <company-insurance-modal ref="modalForm" @ok="modalFormOk"></company-insurance-modal>
  </a-card>
</template>

<script>

  import '@/assets/less/TableExpand.less'
  import { mixinDevice } from '@/utils/mixin'
  import { JeecgListMixin } from '@/mixins/JeecgListMixin'
  import CompanyInsuranceModal from './modules/CompanyInsuranceModal'

  export default {
    name: 'CompanyInsuranceList',
    mixins:[JeecgListMixin, mixinDevice],
    components: {
      CompanyInsuranceModal
    },
    data () {
      return {
        description: '保险公司保单管理页面',
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
            title:'投保单号',
            align:"center",
            dataIndex: 'insureOddNum'
          },
          {
            title:'保单号 （交强，商业）',
            align:"center",
            dataIndex: 'insureNum'
          },
          {
            title:'险类名称',
            align:"center",
            dataIndex: 'insureTypeName'
          },
          {
            title:'险种代码',
            align:"center",
            dataIndex: 'insureProductCode'
          },
          {
            title:'险种',
            align:"center",
            dataIndex: 'insureProductName'
          },
          {
            title:'签单日期',
            align:"center",
            dataIndex: 'insureSignDate',
            customRender:function (text) {
              return !text?"":(text.length>10?text.substr(0,10):text)
            }
          },
          {
            title:'转保单时间（出单时间）',
            align:"center",
            dataIndex: 'zbTime',
            customRender:function (text) {
              return !text?"":(text.length>10?text.substr(0,10):text)
            }
          },
          {
            title:'转保单日期（出单日期）',
            align:"center",
            dataIndex: 'zbDate',
            customRender:function (text) {
              return !text?"":(text.length>10?text.substr(0,10):text)
            }
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
            title:'起保小时',
            align:"center",
            dataIndex: 'insureStartHours'
          },
          {
            title:'终保小时',
            align:"center",
            dataIndex: 'insureEndHours'
          },
          {
            title:'终保日期',
            align:"center",
            dataIndex: 'insureEndDate',
            customRender:function (text) {
              return !text?"":(text.length>10?text.substr(0,10):text)
            }
          },
          {
            title:'签单保费（含税）',
            align:"center",
            dataIndex: 'insureFeeIncludeTax'
          },
          {
            title:'签单保费（不含税）',
            align:"center",
            dataIndex: 'insureFeeExcludeTax'
          },
          {
            title:'已结金额',
            align:"center",
            dataIndex: 'alreadyPaymentFee'
          },
          {
            title:'未结金额',
            align:"center",
            dataIndex: 'unpaidFee'
          },
          {
            title:'已赚保费',
            align:"center",
            dataIndex: 'earnMoney'
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
            title:'出单员',
            align:"center",
            dataIndex: 'salesMan'
          },
          {
            title:'投保人名称',
            align:"center",
            dataIndex: 'policyHolder'
          },
          {
            title:'被保险人名称',
            align:"center",
            dataIndex: 'insurant'
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
            title:'三责保额',
            align:"center",
            dataIndex: 'thirdPartyInsured'
          },
          {
            title:'三责保费',
            align:"center",
            dataIndex: 'thirdPartyFee'
          },
          {
            title:'发动机号',
            align:"center",
            dataIndex: 'engineNumber'
          },
          {
            title:'车牌号',
            align:"center",
            dataIndex: 'vehicleLicense'
          },
          {
            title:'车架号',
            align:"center",
            dataIndex: 'vehicleIdentity'
          },
          {
            title:'渠道码',
            align:"center",
            dataIndex: 'distributionChannelCode'
          },
          {
            title:'渠道名称',
            align:"center",
            dataIndex: 'distributionChannelName'
          },
          {
            title:'渠道类型',
            align:"center",
            dataIndex: 'distributionChannelType'
          },
          {
            title:'座位',
            align:"center",
            dataIndex: 'seatsNum'
          },
          {
            title:'吨位',
            align:"center",
            dataIndex: 'ton'
          },
          {
            title:'送修码名称',
            align:"center",
            dataIndex: 'repairName'
          },
          {
            title:'送修码',
            align:"center",
            dataIndex: 'repairCode'
          },
          {
            title:'滞纳金',
            align:"center",
            dataIndex: 'lateFee'
          },
          {
            title:'车主',
            align:"center",
            dataIndex: 'carOwner'
          },
          {
            title:'最新归属机构代码',
            align:"center",
            dataIndex: 'newOrganizationCode'
          },
          {
            title:'最新归属业务员',
            align:"center",
            dataIndex: 'newSalesman'
          },
          {
            title:'最新渠道码',
            align:"center",
            dataIndex: 'newChannalCode'
          },
          {
            title:'最新渠道名称',
            align:"center",
            dataIndex: 'newChannalName'
          },
          {
            title:'是否录入过户标志',
            align:"center",
            dataIndex: 'isTransfer'
          },
          {
            title:'过户日期',
            align:"center",
            dataIndex: 'transferDate',
            customRender:function (text) {
              return !text?"":(text.length>10?text.substr(0,10):text)
            }
          },
          {
            title:'是否微信绑定客户',
            align:"center",
            dataIndex: 'bindWechat'
          },
          {
            title:'承保险别名称',
            align:"center",
            dataIndex: 'insuranceAlias'
          },
          {
            title:'微信绑定日期',
            align:"center",
            dataIndex: 'bindWechatDate',
            customRender:function (text) {
              return !text?"":(text.length>10?text.substr(0,10):text)
            }
          },
          {
            title:'车损险保额',
            align:"center",
            dataIndex: 'carDamageInsured'
          },
          {
            title:'是否电子投保单',
            align:"center",
            dataIndex: 'isElectronicInsurance'
          },
          {
            title:'是否使用电子保单',
            align:"center",
            dataIndex: 'isUsedElectronic'
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
            title:'上年终保日期',
            align:"center",
            dataIndex: 'lastYearInvalid',
            customRender:function (text) {
              return !text?"":(text.length>10?text.substr(0,10):text)
            }
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
          list: "/companyInsurance/companyInsurance/list",
          delete: "/companyInsurance/companyInsurance/delete",
          deleteBatch: "/companyInsurance/companyInsurance/deleteBatch",
          exportXlsUrl: "/companyInsurance/companyInsurance/exportXls",
          importExcelUrl: "companyInsurance/companyInsurance/importExcel",
          
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
        fieldList.push({type:'string',value:'insureOddNum',text:'投保单号',dictCode:''})
        fieldList.push({type:'string',value:'insureNum',text:'保单号 （交强，商业）',dictCode:''})
        fieldList.push({type:'string',value:'insureTypeName',text:'险类名称',dictCode:''})
        fieldList.push({type:'string',value:'insureProductCode',text:'险种代码',dictCode:''})
        fieldList.push({type:'string',value:'insureProductName',text:'险种',dictCode:''})
        fieldList.push({type:'date',value:'insureSignDate',text:'签单日期'})
        fieldList.push({type:'date',value:'zbTime',text:'转保单时间（出单时间）'})
        fieldList.push({type:'date',value:'zbDate',text:'转保单日期（出单日期）'})
        fieldList.push({type:'date',value:'insureStartDate',text:'起保日期'})
        fieldList.push({type:'int',value:'insureStartHours',text:'起保小时',dictCode:''})
        fieldList.push({type:'int',value:'insureEndHours',text:'终保小时',dictCode:''})
        fieldList.push({type:'date',value:'insureEndDate',text:'终保日期'})
        fieldList.push({type:'double',value:'insureFeeIncludeTax',text:'签单保费（含税）',dictCode:''})
        fieldList.push({type:'double',value:'insureFeeExcludeTax',text:'签单保费（不含税）',dictCode:''})
        fieldList.push({type:'double',value:'alreadyPaymentFee',text:'已结金额',dictCode:''})
        fieldList.push({type:'string',value:'unpaidFee',text:'未结金额',dictCode:''})
        fieldList.push({type:'double',value:'earnMoney',text:'已赚保费',dictCode:''})
        fieldList.push({type:'double',value:'signServiceHarge',text:'签单手续费',dictCode:''})
        fieldList.push({type:'string',value:'renewalType',text:'新续保标志',dictCode:''})
        fieldList.push({type:'string',value:'salesMan',text:'出单员',dictCode:''})
        fieldList.push({type:'string',value:'policyHolder',text:'投保人名称',dictCode:''})
        fieldList.push({type:'string',value:'insurant',text:'被保险人名称',dictCode:''})
        fieldList.push({type:'date',value:'registerDate',text:'初登日期'})
        fieldList.push({type:'string',value:'carUsageType',text:'使用性质',dictCode:''})
        fieldList.push({type:'string',value:'vehiclesType',text:'车辆种类',dictCode:''})
        fieldList.push({type:'int',value:'thirdPartyInsured',text:'三责保额',dictCode:''})
        fieldList.push({type:'double',value:'thirdPartyFee',text:'三责保费',dictCode:''})
        fieldList.push({type:'string',value:'engineNumber',text:'发动机号',dictCode:''})
        fieldList.push({type:'string',value:'vehicleLicense',text:'车牌号',dictCode:''})
        fieldList.push({type:'string',value:'vehicleIdentity',text:'车架号',dictCode:''})
        fieldList.push({type:'string',value:'distributionChannelCode',text:'渠道码',dictCode:''})
        fieldList.push({type:'string',value:'distributionChannelName',text:'渠道名称',dictCode:''})
        fieldList.push({type:'string',value:'distributionChannelType',text:'渠道类型',dictCode:''})
        fieldList.push({type:'int',value:'seatsNum',text:'座位',dictCode:''})
        fieldList.push({type:'int',value:'ton',text:'吨位',dictCode:''})
        fieldList.push({type:'string',value:'repairName',text:'送修码名称',dictCode:''})
        fieldList.push({type:'string',value:'repairCode',text:'送修码',dictCode:''})
        fieldList.push({type:'double',value:'lateFee',text:'滞纳金',dictCode:''})
        fieldList.push({type:'string',value:'carOwner',text:'车主',dictCode:''})
        fieldList.push({type:'string',value:'newOrganizationCode',text:'最新归属机构代码',dictCode:''})
        fieldList.push({type:'string',value:'newSalesman',text:'最新归属业务员',dictCode:''})
        fieldList.push({type:'string',value:'newChannalCode',text:'最新渠道码',dictCode:''})
        fieldList.push({type:'string',value:'newChannalName',text:'最新渠道名称',dictCode:''})
        fieldList.push({type:'int',value:'isTransfer',text:'是否录入过户标志',dictCode:''})
        fieldList.push({type:'date',value:'transferDate',text:'过户日期'})
        fieldList.push({type:'string',value:'bindWechat',text:'是否微信绑定客户',dictCode:''})
        fieldList.push({type:'string',value:'insuranceAlias',text:'承保险别名称',dictCode:''})
        fieldList.push({type:'date',value:'bindWechatDate',text:'微信绑定日期'})
        fieldList.push({type:'int',value:'carDamageInsured',text:'车损险保额',dictCode:''})
        fieldList.push({type:'string',value:'isElectronicInsurance',text:'是否电子投保单',dictCode:''})
        fieldList.push({type:'string',value:'isUsedElectronic',text:'是否使用电子保单',dictCode:''})
        fieldList.push({type:'int',value:'driverLiabilityInsure',text:'司机责任险保额',dictCode:''})
        fieldList.push({type:'int',value:'passengerLiability',text:'乘客责任险保额',dictCode:''})
        fieldList.push({type:'date',value:'lastYearInvalid',text:'上年终保日期'})
        this.superFieldList = fieldList
      }
    }
  }
</script>
<style scoped>
  @import '~@assets/less/common.less';
</style>