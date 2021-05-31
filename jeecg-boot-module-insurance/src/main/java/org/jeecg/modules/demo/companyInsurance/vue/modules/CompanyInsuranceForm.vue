<template>
  <a-spin :spinning="confirmLoading">
    <j-form-container :disabled="formDisabled">
      <a-form-model ref="form" :model="model" :rules="validatorRules" slot="detail">
        <a-row>
          <a-col :span="24">
            <a-form-model-item label="投保单号" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="insureOddNum">
              <a-input v-model="model.insureOddNum" placeholder="请输入投保单号"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="保单号 （交强，商业）" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="insureNum">
              <a-input v-model="model.insureNum" placeholder="请输入保单号 （交强，商业）"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="险类名称" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="insureTypeName">
              <a-input v-model="model.insureTypeName" placeholder="请输入险类名称"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="险种代码" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="insureProductCode">
              <a-input v-model="model.insureProductCode" placeholder="请输入险种代码"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="险种" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="insureProductName">
              <a-input v-model="model.insureProductName" placeholder="请输入险种"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="签单日期" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="insureSignDate">
              <j-date placeholder="请选择签单日期" v-model="model.insureSignDate"  style="width: 100%" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="转保单时间（出单时间）" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="zbTime">
              <j-date placeholder="请选择转保单时间（出单时间）" v-model="model.zbTime"  style="width: 100%" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="转保单日期（出单日期）" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="zbDate">
              <j-date placeholder="请选择转保单日期（出单日期）" v-model="model.zbDate"  style="width: 100%" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="起保日期" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="insureStartDate">
              <j-date placeholder="请选择起保日期" v-model="model.insureStartDate"  style="width: 100%" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="起保小时" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="insureStartHours">
              <a-input-number v-model="model.insureStartHours" placeholder="请输入起保小时" style="width: 100%" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="终保小时" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="insureEndHours">
              <a-input-number v-model="model.insureEndHours" placeholder="请输入终保小时" style="width: 100%" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="终保日期" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="insureEndDate">
              <j-date placeholder="请选择终保日期" v-model="model.insureEndDate"  style="width: 100%" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="签单保费（含税）" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="insureFeeIncludeTax">
              <a-input-number v-model="model.insureFeeIncludeTax" placeholder="请输入签单保费（含税）" style="width: 100%" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="签单保费（不含税）" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="insureFeeExcludeTax">
              <a-input-number v-model="model.insureFeeExcludeTax" placeholder="请输入签单保费（不含税）" style="width: 100%" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="已结金额" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="alreadyPaymentFee">
              <a-input-number v-model="model.alreadyPaymentFee" placeholder="请输入已结金额" style="width: 100%" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="未结金额" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="unpaidFee">
              <a-input v-model="model.unpaidFee" placeholder="请输入未结金额"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="已赚保费" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="earnMoney">
              <a-input-number v-model="model.earnMoney" placeholder="请输入已赚保费" style="width: 100%" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="签单手续费" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="signServiceHarge">
              <a-input-number v-model="model.signServiceHarge" placeholder="请输入签单手续费" style="width: 100%" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="新续保标志" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="renewalType">
              <a-input v-model="model.renewalType" placeholder="请输入新续保标志"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="出单员" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="salesMan">
              <a-input v-model="model.salesMan" placeholder="请输入出单员"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="投保人名称" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="policyHolder">
              <a-input v-model="model.policyHolder" placeholder="请输入投保人名称"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="被保险人名称" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="insurant">
              <a-input v-model="model.insurant" placeholder="请输入被保险人名称"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="初登日期" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="registerDate">
              <j-date placeholder="请选择初登日期" v-model="model.registerDate"  style="width: 100%" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="使用性质" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="carUsageType">
              <a-input v-model="model.carUsageType" placeholder="请输入使用性质"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="车辆种类" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="vehiclesType">
              <a-input v-model="model.vehiclesType" placeholder="请输入车辆种类"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="三责保额" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="thirdPartyInsured">
              <a-input-number v-model="model.thirdPartyInsured" placeholder="请输入三责保额" style="width: 100%" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="三责保费" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="thirdPartyFee">
              <a-input-number v-model="model.thirdPartyFee" placeholder="请输入三责保费" style="width: 100%" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="发动机号" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="engineNumber">
              <a-input v-model="model.engineNumber" placeholder="请输入发动机号"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="车牌号" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="vehicleLicense">
              <a-input v-model="model.vehicleLicense" placeholder="请输入车牌号"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="车架号" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="vehicleIdentity">
              <a-input v-model="model.vehicleIdentity" placeholder="请输入车架号"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="渠道码" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="distributionChannelCode">
              <a-input v-model="model.distributionChannelCode" placeholder="请输入渠道码"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="渠道名称" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="distributionChannelName">
              <a-input v-model="model.distributionChannelName" placeholder="请输入渠道名称"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="渠道类型" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="distributionChannelType">
              <a-input v-model="model.distributionChannelType" placeholder="请输入渠道类型"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="座位" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="seatsNum">
              <a-input-number v-model="model.seatsNum" placeholder="请输入座位" style="width: 100%" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="吨位" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="ton">
              <a-input-number v-model="model.ton" placeholder="请输入吨位" style="width: 100%" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="送修码名称" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="repairName">
              <a-input v-model="model.repairName" placeholder="请输入送修码名称"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="送修码" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="repairCode">
              <a-input v-model="model.repairCode" placeholder="请输入送修码"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="滞纳金" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="lateFee">
              <a-input-number v-model="model.lateFee" placeholder="请输入滞纳金" style="width: 100%" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="车主" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="carOwner">
              <a-input v-model="model.carOwner" placeholder="请输入车主"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="最新归属机构代码" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="newOrganizationCode">
              <a-input v-model="model.newOrganizationCode" placeholder="请输入最新归属机构代码"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="最新归属业务员" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="newSalesman">
              <a-input v-model="model.newSalesman" placeholder="请输入最新归属业务员"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="最新渠道码" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="newChannalCode">
              <a-input v-model="model.newChannalCode" placeholder="请输入最新渠道码"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="最新渠道名称" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="newChannalName">
              <a-input v-model="model.newChannalName" placeholder="请输入最新渠道名称"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="是否录入过户标志" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="isTransfer">
              <a-input-number v-model="model.isTransfer" placeholder="请输入是否录入过户标志" style="width: 100%" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="过户日期" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="transferDate">
              <j-date placeholder="请选择过户日期" v-model="model.transferDate"  style="width: 100%" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="是否微信绑定客户" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="bindWechat">
              <a-input v-model="model.bindWechat" placeholder="请输入是否微信绑定客户"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="承保险别名称" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="insuranceAlias">
              <a-input v-model="model.insuranceAlias" placeholder="请输入承保险别名称"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="微信绑定日期" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="bindWechatDate">
              <j-date placeholder="请选择微信绑定日期" v-model="model.bindWechatDate"  style="width: 100%" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="车损险保额" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="carDamageInsured">
              <a-input-number v-model="model.carDamageInsured" placeholder="请输入车损险保额" style="width: 100%" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="是否电子投保单" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="isElectronicInsurance">
              <a-input v-model="model.isElectronicInsurance" placeholder="请输入是否电子投保单"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="是否使用电子保单" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="isUsedElectronic">
              <a-input v-model="model.isUsedElectronic" placeholder="请输入是否使用电子保单"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="司机责任险保额" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="driverLiabilityInsure">
              <a-input-number v-model="model.driverLiabilityInsure" placeholder="请输入司机责任险保额" style="width: 100%" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="乘客责任险保额" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="passengerLiability">
              <a-input-number v-model="model.passengerLiability" placeholder="请输入乘客责任险保额" style="width: 100%" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="上年终保日期" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="lastYearInvalid">
              <j-date placeholder="请选择上年终保日期" v-model="model.lastYearInvalid"  style="width: 100%" />
            </a-form-model-item>
          </a-col>
        </a-row>
      </a-form-model>
    </j-form-container>
  </a-spin>
</template>

<script>

  import { httpAction, getAction } from '@/api/manage'
  import { validateDuplicateValue } from '@/utils/util'

  export default {
    name: 'CompanyInsuranceForm',
    components: {
    },
    props: {
      //表单禁用
      disabled: {
        type: Boolean,
        default: false,
        required: false
      }
    },
    data () {
      return {
        model:{
         },
        labelCol: {
          xs: { span: 24 },
          sm: { span: 5 },
        },
        wrapperCol: {
          xs: { span: 24 },
          sm: { span: 16 },
        },
        confirmLoading: false,
        validatorRules: {
        },
        url: {
          add: "/companyInsurance/companyInsurance/add",
          edit: "/companyInsurance/companyInsurance/edit",
          queryById: "/companyInsurance/companyInsurance/queryById"
        }
      }
    },
    computed: {
      formDisabled(){
        return this.disabled
      },
    },
    created () {
       //备份model原始值
      this.modelDefault = JSON.parse(JSON.stringify(this.model));
    },
    methods: {
      add () {
        this.edit(this.modelDefault);
      },
      edit (record) {
        this.model = Object.assign({}, record);
        this.visible = true;
      },
      submitForm () {
        const that = this;
        // 触发表单验证
        this.$refs.form.validate(valid => {
          if (valid) {
            that.confirmLoading = true;
            let httpurl = '';
            let method = '';
            if(!this.model.id){
              httpurl+=this.url.add;
              method = 'post';
            }else{
              httpurl+=this.url.edit;
               method = 'put';
            }
            httpAction(httpurl,this.model,method).then((res)=>{
              if(res.success){
                that.$message.success(res.message);
                that.$emit('ok');
              }else{
                that.$message.warning(res.message);
              }
            }).finally(() => {
              that.confirmLoading = false;
            })
          }
         
        })
      },
    }
  }
</script>