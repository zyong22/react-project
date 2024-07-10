import axios from "axios";
import React, { useEffect, useState } from "react";
import { Button } from "react-bootstrap";
import { Link, useParams } from "react-router-dom";

const Detail = (props) => {
  const { id } = useParams();

  const [board, setBoard] = useState({
    id: undefined,
    title: "",
    content: "",
    userId: undefined,
    username: "",
    owner: false,
    replies: [],
  });

  useEffect(() => {
    fetchDetail(id);
  }, []);

  async function fetchDetail(boardId) {
    let response = await axios({
      url: `http://localhost:8080/api/boards/${boardId}/detail`,
    });
    let responseBody = response.data;

    setBoard(responseBody.body);
  }

  function fetchDelete(boardId) {}

  return (
    <div>
      <Link to={`/updateForm/${board.id}`} className="btn btn-warning">
        수정
      </Link>
      <Button className="btn btn-danger" onClick={() => fetchDelete(board.id)}>
        삭제
      </Button>

      <br />
      <br />
      <h1>{board.title}</h1>
      <hr />
      <div>{board.content}</div>
    </div>
  );
};

export default Detail;