import { client } from './index';

export const signup = async (form) => {
  try {
    const { data } = await client.post('/auth/signup', { ...form });
    return data;
  } catch (error) {
    return error.response.data;
  }
};

export const login = async (form) => {
  try {
    const { data } = await client.post('/auth/login', { ...form });
    return data;
  } catch (error) {
    return error.response.data;
  }
};

export const logout = async () => {
  try {
    const { data } = await client.post('/auth/logout');
    return data;
  } catch (error) {
    return error.response.data;
  }
};
