const board = document.querySelector('.board');
const cells = document.querySelectorAll('.cell');
const message = document.getElementById('message');
const restartBtn = document.getElementById('restartBtn');

let currentPlayer = 'X';
let gameActive = true;
let gameBoard = [
    ['', '', ''],
    ['', '', ''],
    ['', '', '']
];

// Function to check for win or draw
const checkWin = () => {
    const winConditions = [
        [ [0, 0], [0, 1], [0, 2] ],
        [ [1, 0], [1, 1], [1, 2] ],
        [ [2, 0], [2, 1], [2, 2] ],
        [ [0, 0], [1, 0], [2, 0] ],
        [ [0, 1], [1, 1], [2, 1] ],
        [ [0, 2], [1, 2], [2, 2] ],
        [ [0, 0], [1, 1], [2, 2] ],
        [ [0, 2], [1, 1], [2, 0] ]
    ];

    for (let condition of winConditions) {
        const [a, b, c] = condition;
        if (gameBoard[a[0]][a[1]] === currentPlayer && 
            gameBoard[b[0]][b[1]] === currentPlayer && 
            gameBoard[c[0]][c[1]] === currentPlayer) {
            message.innerHTML = `Player ${currentPlayer} wins!`;
            gameActive = false;
            return true;
        }
    }

    if (gameBoard.flat().every(cell => cell !== '')) {
        message.innerHTML = 'It\'s a draw!';
        gameActive = false;
        return true;
    }

    return false;
};

// Function to handle cell click
const handleCellClick = (e) => {
    const row = e.target.getAttribute('data-row');
    const col = e.target.getAttribute('data-col');

    if (gameBoard[row][col] === '' && gameActive) {
        gameBoard[row][col] = currentPlayer;
        e.target.textContent = currentPlayer;
        if (!checkWin()) {
            currentPlayer = currentPlayer === 'X' ? 'O' : 'X';
            message.innerHTML = `Player ${currentPlayer}'s turn`;
        }
    }
};

// Function to restart the game
const restartGame = () => {
    currentPlayer = 'X';
    gameBoard = [
        ['', '', ''],
        ['', '', ''],
        ['', '', '']
    ];
    gameActive = true;
    cells.forEach(cell => {
        cell.textContent = '';
    });
    message.innerHTML = `Player X's turn`;
};

// Event listeners
cells.forEach(cell => {
    cell.addEventListener('click', handleCellClick);
});

restartBtn.addEventListener('click', restartGame);
