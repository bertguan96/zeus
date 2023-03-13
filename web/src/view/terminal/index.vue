<template>
  <div id="terminal">
    <Toolbar :toolbarConfig="this.config"/>
    <Console/>
  </div>
</template>

<script>
import {toolbarConfig} from './config.js'
import Toolbar from "@/view/toolbar/index.vue";
import Console from "@/view/console/index.vue";
import SockJS from 'sockjs-client';
import Stomp from 'stompjs';

export default {
  name: 'terminal-demo',
  components: {Console, Toolbar},
  data() {
    return {
      config: toolbarConfig,
    }
  },

}
</script>

<style>
#terminal {
  height: 700px;
  width: 1000px;
  margin: 0 auto;
}
</style>