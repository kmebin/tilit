import { client } from './index';

export const getCourses = async () => {
  const { data } = await client.get('/courses');
  return data.data;
};
