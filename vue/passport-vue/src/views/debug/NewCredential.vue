<style scoped>
.el {
  margin: 10px;
}
</style>

<template>
  <div class="about">
    <h1>New Credential</h1>

    <div>
      <el-input class="el" v-model="email" placeholder="Email" />
      <el-input class="el" v-model="domain" type="textarea" placeholder="网址/域名" />
      <el-input class="el" v-model="user" placeholder="用户名" />
      <el-input class="el" v-model="label" placeholder="标签" />
      <el-input class="el" v-model="description" placeholder="备注" />
      <el-button class="el" type="success" @click="onClickSave">保存</el-button>
    </div>
  </div>
</template>

<script>
export default {
  name: "NewCredential",
  data() {
    return {
      email: "tester@test.com",
      domain: "test.domain.com",
      user: "tester",
      label: "ce shi",
      description: "zhe shi yi ge ce shi."
    };
  },
  methods: {
    onClickSave() {
      var account = this.email;
      var alias = this.user;
      var description = this.description;
      var label = this.label;
      var domain = this.domain;

      var method = "POST";
      var type = "Credential";
      var id = null;
      var content = {
        credential: { account, alias, domain, label, description }
      };
      this.$store.dispatch("rest", { method, type, id, content });
    }
  }
};
</script>