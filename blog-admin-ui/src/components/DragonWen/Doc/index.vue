<template>
  <div>
    <svg-icon icon-class="question" @click="goto"/>
  </div>
</template>

<script>
export default {
  name: 'DragonWenDoc',
  data() {
    return {
      url: 'https://gitee.com/wenMN1994/dragon_blog'
    }
  },
  methods: {
    goto() {
      window.open(this.url)
    }
  }
}
</script>
