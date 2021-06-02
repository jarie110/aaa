<template>
  <a-spin :spinning="confirmLoading">
    <j-form-container :disabled="formDisabled">
      <a-form-model ref="form" :model="model" :rules="validatorRules" slot="detail">
        <a-row>
          <a-col :span="24">
            <a-form-model-item label="出单日期" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="insuranceDate">
              <j-date placeholder="请选择出单日期" v-model="model.insuranceDate"  style="width: 100%" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="出单员" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="salesman">
              <a-input v-model="model.salesman" placeholder="请输入出单员"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="车牌号" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="vehicleLicense">
              <a-input v-model="model.vehicleLicense" placeholder="请输入车牌号"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="客户名称" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="customer">
              <a-input v-model="model.customer" placeholder="请输入客户名称"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="车架号" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="vehicleIdentity">
              <a-input v-model="model.vehicleIdentity" placeholder="请输入车架号"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="交强险保单号" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="compulsoryInsurCode">
              <a-input v-model="model.compulsoryInsurCode" placeholder="请输入交强险保单号"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="交强险保费" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="compulsoryInsurFee">
              <a-input-number v-model="model.compulsoryInsurFee" placeholder="请输入交强险保费" style="width: 100%" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="商业险保单号" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="commercialInsurCode">
              <a-input v-model="model.commercialInsurCode" placeholder="请输入商业险保单号"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="商业险保费" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="commercialInsurFee">
              <a-input-number v-model="model.commercialInsurFee" placeholder="请输入商业险保费" style="width: 100%" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="车船税" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="vehicleVesselTax">
              <a-input-number v-model="model.vehicleVesselTax" placeholder="请输入车船税" style="width: 100%" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="保费总额" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="insuranceTotalFee">
              <a-input-number v-model="model.insuranceTotalFee" placeholder="请输入保费总额" style="width: 100%" disabled/>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="手续费总额" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="totalServiceFee">
              <a-input-number v-model="model.totalServiceFee" placeholder="请输入手续费总额" style="width: 100%" disabled/>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="新续保标志" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="renewalType">
              <j-dict-select-tag type="list" v-model="model.renewalType" dictCode="renewal_symbol" placeholder="请选择新续保标志" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="使用性质" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="carUsageType">
              <j-dict-select-tag type="list" v-model="model.carUsageType" dictCode="insurance_usage,usage_name,usage_type" placeholder="请选择使用性质" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="渠道名称" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="distributionChannelId">
              <j-dict-select-tag type="list" v-model="model.distributionChannelId" dictCode="distribution_channel,channel_name,channel_type" placeholder="请选择渠道名称" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="备注" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="remark">
              <a-input v-model="model.remark" placeholder="请输入备注"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="所属团队" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="insuranceTeam">
              <j-dict-select-tag type="list" v-model="model.insuranceTeam" dictCode="insurance_team,team_name,team_code" placeholder="请选择所属团队" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="三者责任险保额" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="thirdPartyInsured">
              <a-input-number v-model="model.thirdPartyInsured" placeholder="请输入三者责任险保额" style="width: 100%" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="司机责任险保额" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="driverLiabilityInsure">
              <a-input-number v-model="model.driverLiabilityInsure" placeholder="请输入司机责任险保额" style="width: 100%" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="车损险保额" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="carDamageInsured">
              <a-input-number v-model="model.carDamageInsured" placeholder="请输入车损险保额" style="width: 100%" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="乘客责任险保额" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="passengerLiability">
              <a-input-number v-model="model.passengerLiability" placeholder="请输入乘客责任险保额" style="width: 100%" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="初登日期" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="registerDate">
              <j-date placeholder="请选择初登日期" v-model="model.registerDate"  style="width: 100%" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="是否过户" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="isTransfer">
              <j-dict-select-tag type="list" v-model="model.isTransfer" dictCode="is_transfer" placeholder="请选择是否过户" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="签单手续费" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="signFee">
              <a-input-number v-model="model.signFee" placeholder="请输入签单手续费" style="width: 100%" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="座位数" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="seatsNum">
              <a-input-number v-model="model.seatsNum" placeholder="请输入座位数" style="width: 100%" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="交强险返点比" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="compulsoryInsuranceRebate">
              <a-input-number v-model="model.compulsoryInsuranceRebate" placeholder="请输入交强险返点比" style="width: 100%" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="商业险返点比" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="commercialInsuranceRebate">
              <a-input-number v-model="model.commercialInsuranceRebate" placeholder="请输入商业险返点比" style="width: 100%" disabled/>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="是否返佣" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="isPayCommission">
              <j-dict-select-tag type="list" v-model="model.isPayCommission" dictCode="is_paid_rebate" placeholder="请选择是否返佣" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="是否比对" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="isChecked">
              <j-dict-select-tag type="list" v-model="model.isChecked" dictCode="is_checked" placeholder="请选择是否比对" disabled/>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="座位保奖励金" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="seatBonus">
              <a-input-number v-model="model.seatBonus" placeholder="请输入座位保奖励金" style="width: 100%" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="返点支付方式" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="paymentWay">
              <j-dict-select-tag type="list" v-model="model.paymentWay" dictCode="rebate_way" placeholder="请选择返点支付方式" />
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
    name: 'InsuranceInHandForm',
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
            isPayCommission:0,
            isChecked:0,
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
           insuranceDate: [
              { required: true, message: '请输入出单日期!'},
           ],
           salesman: [
              { required: true, message: '请输入出单员!'},
           ],
           vehicleLicense: [
              { required: true, message: '请输入车牌号!'},
           ],
           customer: [
              { required: true, message: '请输入客户名称!'},
           ],
           vehicleIdentity: [
              { required: true, message: '请输入车架号!'},
           ],
           compulsoryInsurCode: [
              { required: true, message: '请输入交强险保单号!'},
           ],
           compulsoryInsurFee: [
              { required: true, message: '请输入交强险保费!'},
           ],
           commercialInsurCode: [
              { required: true, message: '请输入商业险保单号!'},
           ],
           commercialInsurFee: [
              { required: true, message: '请输入商业险保费!'},
           ],
           vehicleVesselTax: [
              { required: true, message: '请输入车船税!'},
           ],
           renewalType: [
              { required: true, message: '请输入新续保标志!'},
           ],
           carUsageType: [
              { required: true, message: '请输入使用性质!'},
           ],
           insuranceTeam: [
              { required: true, message: '请输入所属团队!'},
           ],
           registerDate: [
              { required: true, message: '请输入初登日期!'},
           ],
           isTransfer: [
              { required: true, message: '请输入是否过户!'},
           ],
           signFee: [
              { required: true, message: '请输入签单手续费!'},
           ],
           seatsNum: [
              { required: true, message: '请输入座位数!'},
           ],
           isPayCommission: [
              { required: true, message: '请输入是否返佣!'},
           ],
           isChecked: [
              { required: true, message: '请输入是否比对!'},
           ],
        },
        url: {
          add: "/proxyInsurance/insuranceInHand/add",
          edit: "/proxyInsurance/insuranceInHand/edit",
          queryById: "/proxyInsurance/insuranceInHand/queryById"
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