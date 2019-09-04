<template>
  <div class="unnamed">
    <h1>Sign Up</h1>

    <el-input v-model="email" placeholder="电子邮箱"></el-input>
    <el-input v-model="verification" placeholder="验证码"></el-input>
    <el-button @click="onClickButtonGetCode">获取验证码</el-button>
    <el-button @click="onClickButtonSignUp">注册</el-button>
  </div>
</template>

<script>
export default {
  name: "SignUp",
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
      this.$store.dispatch("sign_up/step1", { email });
    },

    onClickButtonSignUp() {
      var verification = this.verification;
      this.$store.dispatch("sign_up/step2", { verification });
    }
  }
};
</script>
