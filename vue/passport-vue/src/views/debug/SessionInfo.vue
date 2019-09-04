<style scoped>
.el {
  margin: 10px;
}
</style>

<template>
  <div class="session-info">
    <el-button @click="refresh">Refresh</el-button>
  </div>
</template>

<script>
export default {
  name: "SessionInfo",
  data() {
    return {};
  },
  mounted() {
    this.refresh();
  },
  methods: {
    refresh() {
      var method = "get";
      var type = "Session";
      var id = "self";
      var content = null;
      this.$store.dispatch("rest", { method, type, id, content });
    }
  }
};
</script>