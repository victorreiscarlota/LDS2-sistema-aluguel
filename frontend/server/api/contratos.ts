import { createApiHandler } from '@/utils/createApiHandler';

const config = useRuntimeConfig();
const apiURL = `${config.public.baseURL}/contratos`;

export default createApiHandler(apiURL);
