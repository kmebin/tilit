import { client } from './index';

export const getCourses = async (category, keyword) => {
  try {
    const params = { category, keyword };
    const { data } = await client.get('/courses', { params });
    return data;
  } catch (error) {
    return error.response.data;
  }
};

export const getCourseDetail = async (courseId) => {
  try {
    const { data } = await client.get(`/courses/${courseId}`);
    return data;
  } catch (error) {
    return error.response.data;
  }
};

export const createCourse = async (form) => {
  try {
    const { data } = await client.post('/courses', { ...form });
    return data;
  } catch (error) {
    return error.response.data;
  }
};

export const updateCourse = async (courseId, form) => {
  try {
    const { data } = await client.patch(`/courses/${courseId}`, { ...form });
    return data;
  } catch (error) {
    return error.response.data;
  }
};

export const deleteCourse = async (courseId) => {
  try {
    const { data } = await client.delete(`/courses/${courseId}`);
    return data;
  } catch (error) {
    return error.response.data;
  }
};

export const registerCourses = async (courseIds) => {
  try {
    const { data } = await client.post('/courses/register', { courseIds });
    return data;
  } catch (error) {
    return error.response.data;
  }
};
