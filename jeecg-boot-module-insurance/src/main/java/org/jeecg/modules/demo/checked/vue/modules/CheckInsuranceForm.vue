<template>
  <a-spin :spinning="confirmLoading">
    <j-form-container :disabled="formDisabled">
      <a-form-model ref="form" :model="model" :rules="validatorRules" slot="detail">
        <a-row>
          <a-col :span="24">
            <a-form-model-item label="保单编号" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="insureNum">
              <a-input v-model="model.insureNum" placeholder="请输入保单编号" disabled ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="录入日期" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="inputInsuranceDate">
              <j-date placeholder="请选择录入日期" v-model="model.inputInsuranceDate"  style="width: 100%" disabled/>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="比对日期" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="checkDate">
              <j-date placeholder="请选择比对日期" v-model="model.checkDate"  style="width: 100%" disabled/>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="出单日期" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="insuranceDate">
              <j-date placeholder="请选择出单日期" v-model="model.insuranceDate"  style="width: 100%" disabled/>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="出单员" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="salesman">
              <a-input v-model="model.salesman" placeholder="请输入出单员" disabled ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="所属团队" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="insuranceTeam">
              <a-input v-model="model.insuranceTeam" placeholder="请输入所属团队" disabled ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="客户名称" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="customerName">
              <a-input v-model="model.customerName" placeholder="请输入客户名称" disabled ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="车架号" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="vehicleIdentity">
              <a-input v-model="model.vehicleIdentity" placeholder="请输入车架号" disabled ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="交强险保单号" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="compulsoryInsurCode">
              <a-input v-model="model.compulsoryInsurCode" placeholder="请输入交强险保单号" disabled ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="商业险保单号" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="commercialInsurCode">
              <a-input v-model="model.commercialInsurCode" placeholder="请输入商业险保单号" disabled ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="交强险保费" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="compulsoryInsurFee">
              <a-input-number v-model="model.compulsoryInsurFee" placeholder="请输入交强险保费" style="width: 100%" disabled/>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="保费总额" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="insuranceTotalFee">
              <a-input-number v-model="model.insuranceTotalFee" placeholder="请输入保费总额" style="width: 100%" disabled/>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="交强险手续费比例" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="compulsoryInsuranceRebate">
              <a-input-number v-model="model.compulsoryInsuranceRebate" placeholder="请输入交强险手续费比例" style="width: 100%" disabled/>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="商业险手续费比例" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="commercialInsuranceRebate">
              <a-input-number v-model="model.commercialInsuranceRebate" placeholder="请输入商业险手续费比例" style="width: 100%" disabled/>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="额外奖励金额" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="bonus">
              <a-input-number v-model="model.bonus" placeholder="请输入额外奖励金额" style="width: 100%" disabled/>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="渠道名称" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="distributionChannelId">
              <a-input v-model="model.distributionChannelId" placeholder="请输入渠道名称" disabled ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="起保日期" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="insureStartDate">
              <j-date placeholder="请选择起保日期" v-model="model.insureStartDate"  style="width: 100%" disabled/>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="初登日期" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="registerDate">
              <j-date placeholder="请选择初登日期" v-model="model.registerDate"  style="width: 100%" disabled/>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="签单手续费" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="signServiceHarge">
              <a-input-number v-model="model.signServiceHarge" placeholder="请输入签单手续费" style="width: 100%" disabled/>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="新续保标志" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="renewalType">
              <a-input v-model="model.renewalType" placeholder="请输入新续保标志" disabled ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="使用性质" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="carUsageType">
              <a-input v-model="model.carUsageType" placeholder="请输入使用性质" disabled ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="车辆种类" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="vehiclesType">
              <a-input v-model="model.vehiclesType" placeholder="请输入车辆种类" disabled ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="是否录入过户标志" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="isTransfer">
              <a-input v-model="model.isTransfer" placeholder="请输入是否录入过户标志" disabled ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="三责保额" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="thirdPartyInsured">
              <a-input-number v-model="model.thirdPartyInsured" placeholder="请输入三责保额" style="width: 100%" disabled/>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="车损险保额" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="carDamageInsured">
              <a-input-number v-model="model.carDamageInsured" placeholder="请输入车损险保额" style="width: 100%" disabled/>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="承保险别名称" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="insuranceAlias">
              <a-input v-model="model.insuranceAlias" placeholder="请输入承保险别名称" disabled ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="座位" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="seatsNum">
              <a-input-number v-model="model.seatsNum" placeholder="请输入座位" style="width: 100%" disabled/>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="司机责任险保额" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="driverLiabilityInsure">
              <a-input-number v-model="model.driverLiabilityInsure" placeholder="请输入司机责任险保额" style="width: 100%" disabled/>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="乘客责任险保额" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="passengerLiability">
              <a-input-number v-model="model.passengerLiability" placeholder="请输入乘客责任险保额" style="width: 100%" disabled/>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="手续费总额" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="totalServiceFee">
              <a-input-number v-model="model.totalServiceFee" placeholder="请输入手续费总额" style="width: 100%" disabled/>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="是否返点" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="isRebate">
              <j-dict-select-tag type="list" v-model="model.isRebate" dictCode="" placeholder="请选择是否返点" disabled/>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="返点支付时间" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="rebatePaymentDate">
              <j-date placeholder="请选择返点支付时间" v-model="model.rebatePaymentDate"  style="width: 100%" disabled/>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="返点收取方式" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="rebateWay">
              <a-input-number v-model="model.rebateWay" placeholder="请输入返点收取方式" style="width: 100%" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="车牌号" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="vehicleLicense">
              <a-input v-model="model.vehicleLicense" placeholder="请输入车牌号" disabled ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="商业险保费" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="commercialInsurFee">
              <a-input-number v-model="model.commercialInsurFee" placeholder="请输入商业险保费" style="width: 100%" disabled/>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="车船税" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="vehicleVesselTax">
              <a-input-number v-model="model.vehicleVesselTax" placeholder="请输入车船税" style="width: 100%" disabled/>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="备注" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="remarks">
              <a-textarea v-model="model.remarks" rows="4" placeholder="请输入备注" disabled/>
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
    name: 'CheckInsuranceForm',
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
           compulsoryInsurFee: [
              { required: false},
              { pattern: /^(([1-9][0-9]*)|([0]\.\d{0,2}|[1-9][0-9]*\.\d{0,2}))$/, message: '请输入正确的金额!'},
           ],
           bonus: [
              { required: false},
              { pattern: /^(([1-9][0-9]*)|([0]\.\d{0,2}|[1-9][0-9]*\.\d{0,2}))$/, message: '请输入正确的金额!'},
           ],
           signServiceHarge: [
              { required: false},
              { pattern: /^(([1-9][0-9]*)|([0]\.\d{0,2}|[1-9][0-9]*\.\d{0,2}))$/, message: '请输入正确的金额!'},
           ],
           totalServiceFee: [
              { required: false},
              { pattern: /^(([1-9][0-9]*)|([0]\.\d{0,2}|[1-9][0-9]*\.\d{0,2}))$/, message: '请输入正确的金额!'},
           ],
        },
        url: {
          add: "/checked/checkInsurance/add",
          edit: "/checked/checkInsurance/edit",
          queryById: "/checked/checkInsurance/queryById"
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