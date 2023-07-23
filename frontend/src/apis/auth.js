import { client } from './index';

export const signup = async (form) => {
  return await client.post('/auth/signup', { ...form });
};
