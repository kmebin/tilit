import { client } from './index';

export const getCourses = async () => {
  const { data } = await client.get('/courses');
  return data.data;
};

export const registerCourses = async (courseIds) => {
  const { data } = await client.post('/courses/register', { courseIds });
  return data.data;
};
