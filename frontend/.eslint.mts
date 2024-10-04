module.exports = {
  // ... outras configurações
  extends: ['plugin:vue/vue3-recommended', 'eslint:recommended', 'prettier'],
  plugins: ['vue', 'prettier'],
  rules: {
    'prettier/prettier': 'error',
    // Outras regras personalizadas
  },
};
