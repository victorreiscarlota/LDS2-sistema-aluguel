import { createApiHandler } from '@/utils/createApiHandler';

const config = useRuntimeConfig();
const apiURL = `${config.public.baseURL}/automoveis`;

export default createApiHandler(apiURL);