import { createApiHandler } from '@/utils/createApiHandler';

const config = useRuntimeConfig();
const apiURL = `${config.public.baseURL}/creditos`;

export default createApiHandler(apiURL);