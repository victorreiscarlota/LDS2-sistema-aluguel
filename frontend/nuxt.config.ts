import ptBR from './config/locales/pt-BR.json'

export default defineNuxtConfig({
  compatibilityDate: '2024-04-03',
  devtools: { enabled: true },
  modules: [
    '@primevue/nuxt-module',
    '@nuxtjs/tailwindcss',
    '@nuxtjs/color-mode',
  ],
  runtimeConfig: {
    public: {
      baseURL: process.env.BASE_URL || 'http://localhost'
    }
  },
  primevue: {
    importTheme: { from: '@/config/themes/Brescia.js' },
    options: {
        ripple: false,
        locale: { ...ptBR },
        theme: {
            options: {
              darkModeSelector: '.dark',
            }
        },
    },
  },
  colorMode: {
    preference: 'system',
    fallback: 'light',
    classSuffix: '',
  },
  tailwindcss: {
    cssPath: ["@/assets/css/main.css", { injectPosition: 0 }] ,
    configPath: '@/tailwind.config.ts',
    exposeConfig: false,
    viewer: true,
  },
  css: [
    'primeicons/primeicons.css',
    '@/assets/css/main.css'
  ],
  build: {
    transpile: ['primevue'],
  }
})