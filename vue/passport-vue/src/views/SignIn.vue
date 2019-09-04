
<template>
  <div class="unnamed">
    <h1>Sign In</h1>

    <el-input v-model="email" placeholder="电子邮箱"></el-input>
    <el-input v-model="verification" placeholder="验证码"></el-input>
    <el-button @click="onClickButtonGetCode">获取验证码</el-button>
    <el-button @click="onClickButtonSign"> 登录 </el-button>
  </div>
</template>

<script>
export default {
  name: "SignIn",
  components: {},

  data() {
    return {
      email: "",
      verification: ""
    };
  },

  methods: {
    onClickButtonGetCode() {
      var email = this.email;
      this.$store.dispatch("sign_in/step1", { email });
    },

    onClickButtonSign() {
      var verification = this.verification;
      this.$store.dispatch("sign_in/step2", { verification });
    }
  }
};
</script>
