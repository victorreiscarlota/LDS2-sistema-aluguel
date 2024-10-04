import { createApiHandler } from '@/utils/createApiHandler';

const config = useRuntimeConfig();
const apiURL = `${config.public.baseURL}/clientes`;

export default createApiHandler(apiURL);
