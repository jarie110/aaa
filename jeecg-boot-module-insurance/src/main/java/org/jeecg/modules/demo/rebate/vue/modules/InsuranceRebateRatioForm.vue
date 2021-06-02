<template>
  <a-spin :spinning="confirmLoading">
    <j-form-container :disabled="formDisabled">
      <a-form-model ref="form" :model="model" :rules="validatorRules" slot="detail">
        <a-row>
          <a-col :span="24">
            <a-form-model-item label="返点类型" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="rebateRatioType">
              <j-dict-select-tag type="list" v-model="model.rebateRatioType" dictCode="rebate_ratio_type" placeholder="请选择返点类型" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="是否过户" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="isTransfer">
              <j-dict-select-tag type="list" v-model="model.isTransfer" dictCode="is_transfer" placeholder="请选择是否过户" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="续保类型" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="renewalType">
              <j-dict-select-tag type="list" v-model="model.renewalType" dictCode="renewal_symbol" placeholder="请选择续保类型" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="使用性质" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="usageType">
              <j-dict-select-tag type="list" v-model="model.usageType" dictCode="insurance_usage,usage_name,usage_type" placeholder="请选择使用性质" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="三责保额档" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="thirdPartyInsured">
              <a-input v-model="model.thirdPartyInsured" placeholder="请输入三责保额档"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="车损险保额档" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="carDamageInsured">
              <a-input v-model="model.carDamageInsured" placeholder="请输入车损险保额档"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="司机责任险保额档" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="driverLiabilityInsured">
              <a-input v-model="model.driverLiabilityInsured" placeholder="请输入司机责任险保额档"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="乘客责任险保额档" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="passengerLiability">
              <a-input v-model="model.passengerLiability" placeholder="请输入乘客责任险保额档"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="返点比例" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="rebateRatio">
              <a-input-number v-model="model.rebateRatio" placeholder="请输入返点比例" style="width: 100%" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="奖金" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="bonus">
              <a-input-number v-model="model.bonus" placeholder="请输入奖金" style="width: 100%" />
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
    name: 'InsuranceRebateRatioForm',
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
           rebateRatioType: [
              { required: true, message: '请输入返点类型!'},
           ],
           rebateRatio: [
              { required: true, message: '请输入返点比例!'},
           ],
        },
        url: {
          add: "/rebate/insuranceRebateRatio/add",
          edit: "/rebate/insuranceRebateRatio/edit",
          queryById: "/rebate/insuranceRebateRatio/queryById"
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