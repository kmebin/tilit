import { useState } from 'react';
import { Form, Row, Col, Button } from 'react-bootstrap';

const SearchBar = ({ onClickSearch }) => {
  const [input, setInput] = useState({ category: '', keyword: '' });

  const handleInputCategory = (e) => {
    setInput({ ...input, category: e.target.value });
  };

  const handleInputKeyword = (e) => {
    setInput({ ...input, keyword: e.target.value });
  };

  const handleSearch = () => {
    onClickSearch(input);
  };

  return (
    <Row className='align-items-center'>
      <Col md={4}>
        <Form.Select value={input.category} onChange={handleInputCategory}>
          <option value=''>전체</option>
          <option value='WEB'>웹</option>
          <option value='APP'>앱</option>
          <option value='GAME'>게임</option>
          <option value='ALGORITHM'>알고리즘</option>
          <option value='DATABASE'>데이터베이스</option>
          <option value='INFRA'>인프라</option>
        </Form.Select>
      </Col>
      <Col md={6}>
        <Form.Control
          type='text'
          placeholder='검색어를 입력하세요'
          value={input.keyword}
          onChange={handleInputKeyword}
        />
      </Col>
      <Col md={2}>
        <div className='d-grid'>
          <Button variant='secondary' onClick={handleSearch}>
            검색
          </Button>
        </div>
      </Col>
    </Row>
  );
};

export default SearchBar;
