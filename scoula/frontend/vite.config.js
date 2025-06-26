import { fileURLToPath, URL } from 'node:url';

import { defineConfig } from 'vite';
import vue from '@vitejs/plugin-vue';
import vueDevTools from 'vite-plugin-vue-devtools';

// https://vite.dev/config/
export default defineConfig({
  plugins: [vue(), vueDevTools()],
  resolve: {
    alias: {
      '@': fileURLToPath(new URL('./src', import.meta.url)),
    },
  },
  // 프록시 설정: API 요청을 백엔드로 리다이렉트
  server: {
    proxy: {
      '/api': {
        target: 'http://localhost:8080', // Spring Boot 서버
      },
    },
  },

  // 빌드 결과물 출력 경로 (Spring 프로젝트의 static 위치로 설정)
  build: {
    outDir:
      'C:/fullstack/09_Spring+Vue/scoula/backend/src/main/webapp/resources',
  },
});
