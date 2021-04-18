<template>
  <div>
    <svg-icon icon-class="github" @click="goto"/>
  </div>
</template>

<script>
export default {
  name: 'DragonWenGit',
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
