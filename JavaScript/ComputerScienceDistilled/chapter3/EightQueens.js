function queens(board) {
  numberOfCalls++;
  let solution = [];
  if (numberOfQueens(board) === 8) {
    return board;
  }

  for (const position of unattackedPositions(board)) {
    placeQueen(position, board);
    solution = queens(board);
    if (solution != false) {
      if (numberOfQueens(solution) === 8) {
        return solution;
      }
    }
    removeQueen(position, board);
  }

  return false;
}

let numberOfCalls = 0;

function numberOfQueens(board) {
  let numberOfQueens = 0;
  for (let i = 0; i < 8; i++) {
    for (let j = 0; j < 8; j++) {
      if (board[i][j] === 2) {
        numberOfQueens++;
      }
    }
  }
  return numberOfQueens;
}

let placeQueen = (position, chessBoard) => {
  for (let i = 0; i < 8; i++) {
    chessBoard[position[0]][i] = 1;
    chessBoard[i][position[1]] = 1;
  }

  if (position[0] < position[1]) {
    let i = 0;
    let j = position[1] - position[0];
    while (j < 8) {
      chessBoard[i++][j++] = 1;
    }
  } else {
    let i = position[0] - position[1];
    let j = 0;
    while (i < 8) {
      chessBoard[i++][j++] = 1;
    }
  }

  if (position[0] + position[1] < 8) {
    let i = 0;
    let j = position[0] + position[1];
    while (j >= 0) {
      chessBoard[i++][j--] = 1;
    }
  } else {
    let i = position[0] + position[1] - 7;
    let j = 7;
    while (i < 8) {
      chessBoard[i++][j--] = 1;
    }
  }

  chessBoard[position[0]][position[1]] = 2;
};

function queensPositions(board) {
  let queensPositions = [];

  board.forEach((row, index) => {
    row.forEach((position, indexPosition) => {
      if (position === 2) {
        queensPositions.push([index, indexPosition]);
      }
    });
  });

  return queensPositions;
}

let removeQueen = (position, chessBoard) => {
  chessBoard[position[0]][position[1]] = 0;

  let positions = queensPositions(chessBoard);

  for (let i = 0; i < 8; i++) {
    for (let j = 0; j < 8; j++) {
      chessBoard[i][j] = 0;
    }
  }

  positions.forEach((position) => {
    placeQueen(position, chessBoard);
  });
};

function unattackedPositions(board) {
  let unattackedPositions = [];
  board.forEach((row, index) => {
    row.forEach((position, indexPosition) => {
      if (position === 0) {
        unattackedPositions.push([index, indexPosition]);
      }
    });
  });
  return unattackedPositions;
}

let initialBoard = [
  [0, 0, 0, 0, 0, 0, 0, 0],
  [0, 0, 0, 0, 0, 0, 0, 0],
  [0, 0, 0, 0, 0, 0, 0, 0],
  [0, 0, 0, 0, 0, 0, 0, 0],
  [0, 0, 0, 0, 0, 0, 0, 0],
  [0, 0, 0, 0, 0, 0, 0, 0],
  [0, 0, 0, 0, 0, 0, 0, 0],
  [0, 0, 0, 0, 0, 0, 0, 0],
];

console.log("initial board: ");
console.log(initialBoard);
console.log("final board: ");
console.log(queens(initialBoard));
console.log("number of calls:" + numberOfCalls);
