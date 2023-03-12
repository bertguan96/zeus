<template>
  <div id="toolbar">
    <el-row>
      <el-col :span="3" v-for="(tool, index) in this.toolbarConfig" :key="index">
        <el-button :type="tool.type" plain>{{tool.name}}</el-button>
      </el-col>
    </el-row>
  </div>
</template>

<script>
export default {
  name: 'Toolbar',
  props: ['toolbarConfig'],
  methods: {},
  mounted() {
  }
}
</script>

<style scoped>
#toolbar {
  line-height: 50px;
  width: 100%;
  background: #343535;
}
/deep/ .el-row {
  width: 95%;
  margin: 0 auto;
}
</style>