import { client } from './index';

export const getCourses = async (category, keyword) => {
  const params = { category, keyword };
  const { data } = await client.get('/courses', { params });
  return data.data;
};

export const getCourseDetail = async (courseId) => {
  console.log(courseId);
  const { data } = await client.get(`/courses/${courseId}`);
  return data.data;
};

export const registerCourses = async (courseIds) => {
  const { data } = await client.post('/courses/register', { courseIds });
  return data.data;
};
