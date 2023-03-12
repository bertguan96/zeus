<template>
  <div id='console'>
    <div id='terminal-console'>
    </div>
  </div>
</template>

<script>
export default {
  name: 'console',
  data() {
    return {
      stompClient: null,
      res: null,
      topic_destination_prefixes: '/topic-shanhy',
      websocket: null,
    }
  },
  mounted() {
    this.res = document.getElementById('terminal-console');
    this.websocket = new WebSocket("ws://127.0.0.1:8080/websocket/logger");
    this.renderConsole()
    this.connect()
  },
  methods: {
    renderConsole() {
      const toolbarDom = document.getElementById('toolbar');
      const consoleDom = document.getElementById('console');
      const terminalDom = document.getElementById('terminal');
      const height = terminalDom.clientHeight - toolbarDom.clientHeight
      consoleDom.style.height = height + 'px';
    },
    createDivElement(text) {
      const div = document.createElement('div');
      div.style.color = '#00b43b';
      div.style.fontSize = '14px';
      div.innerText = text;
      return div;
    },
    createSpanElement(text, color) {
      const div = document.createElement('span');
      div.style.color = color;
      div.style.fontSize = '14px';
      div.innerText = text;
      return div;
    },
    connect() {
      this.res.appendChild(this.createDivElement('通道连接成功,静默等待....'))
      const _this = this;
      if (this.websocket != null) {
        this.websocket.open = function () {
          this.websocket.send("数据发送");
        };
        this.websocket.onmessage = function (event) {
          const content = JSON.parse(event.data);
          let levelHtml = _this.createSpanElement(content.level, '#90ad2b');
          const className =  _this.createSpanElement(content.className, '#229379')
          switch (content.level) {
            case 'DEBUG':
              levelHtml = _this.createSpanElement(content.level, '#A8C023');
              break;
            case 'WARN':
              levelHtml = _this.createSpanElement(content.level, '#fffa1c');
              break;
            case 'ERROR':
              levelHtml = _this.createSpanElement(content.level, '#e3270e');
              break;
          }
          const logger = _this.createDivElement(content.timestamp)
          logger.appendChild(levelHtml)
          logger.innerHTML = logger.innerHTML + " --- [" + content.threadName + "] "
          logger.appendChild(className)
          logger.innerHTML = logger.innerHTML + " ：" + content.body
          _this.res.appendChild(logger)
        }
      }
    },
    closeSocket() {
      if (this.stompClient != null) {
        this.stompClient.disconnect();
        this.stompClient = null;
      }
    },
  },
}
</script>

<style scoped>
#console {
  width: 100%;
  background: #0e2a35;
}


#terminal-console {
  width: 95%;
  margin: 0 auto;
  line-height: 20px;
}
</style>