import { createApiHandler } from '@/utils/createApiHandler';

const config = useRuntimeConfig();
const apiURL = `${config.public.baseURL}/pedidos`;

export default createApiHandler(apiURL);