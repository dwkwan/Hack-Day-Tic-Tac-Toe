class TicTacToeMessages {
    def welcome = 'This is TicTacToe';
    def draw = 'Draw!';
    def invalid = 'Invalid position'
    def prompt(currentplayer) {
        if (currentplayer == 1) {
           println 'Pick a position to place an X';
    } else if (currentplayer == 2) {
          println  'Pick a position to place an O';
        }
    }
}
class TicTacToeChecks {
    def num;
    def num_list = []
    def valid (input) {
        try {
            num = input.toInteger();
    } catch(Exception e){
      num = -1;
    }
    if (num < 1 || num > 9)
       return -1;
    return num;
      }
    def check_for_victory(updated_board) {
    	def i, j;
    	for (row in updated_board) {
	    if (row.every{element -> element == 'X'}) {
	       println "Player 1 wins!"
	       return 0
	      } else if (row.every{element -> element == 'O'})
	    {
	       println "Player 2 wins!"
	       return 0
}
}
	for (j= 0; j < 3; j++){
	    i = 0
	    if (updated_board[i][j] == 'X' && updated_board[i + 1][j] == 'X' && updated_board[i + 2][j] == 'X') {
	       println "Player 1 wins!"
	       return 0}
	    if (updated_board[i][j] == 'O' && updated_board[i + 1][j] == 'O' && updated_board[i + 2][j] == 'O') {
	       println "Player 2 wins!"
	       return 0}
    }
	    if (updated_board[0][0] == 'X' && updated_board[1][1] == 'X' && updated_board[2][2] == 'X') {
	       println "Player 1 wins!"
	       return 0}
	    if (updated_board[0][0] == 'O' && updated_board[1][1] == 'O' && updated_board[2][2] == 'O') {
	       println "Player 2 wins!"
	       return 0}
	    if (updated_board[0][2] == 'X' && updated_board[1][1] == 'X' && updated_board[2][0] == 'X') {
	       println "Player 1 wins!"
	       return 0}
	    if (updated_board[0][2] == 'O' && updated_board[1][1] == 'O' && updated_board[2][0] == 'O') {
	       println "Player 2 wins!"
	       return 0}
}
}
class TicTacToeBoard {
      def initial_board = [[1, 2, 3], [4, 5, 6], [7, 8, 9]];
      def setup_board() {
      	  for (row in initial_board) {
              def s = "";
              for (col in row) {
                  s += sprintf("[%s]", col);
                  }
    	println s;
        }
      }
      def display(board) {
          for (row in board) {
          def s = "";
              for (col in row) {
              	  s += sprintf("[%s]", col);
              	  }
	    println s;
        }
    }
    def update(board, num, currentplayer) {
    	def i = 0;
	def j;
	for (row in board) {
	    j = 0;
	    for (col in row) {
	    	if (col == num) {
		   if (currentplayer == 1) {
		      board[i][j] = "X";
		      }
		   else if (currentplayer == 2) {
		      board[i][j] = "O";
		   	}
		}
		j = j + 1;
	}
	i = i + 1;
	}
	return board
    }
}
def TicTacToeFactory = [messages: TicTacToeMessages, checks: TicTacToeChecks, board:TicTacToeBoard ];
class GameFactory {
    def static factory;
    def static getMessages() { return factory.messages.newInstance() };
    def static getChecks() { return factory.checks.newInstance() };
    def static getBoard() { return factory.board.newInstance() };
}
GameFactory.factory = TicTacToeFactory;
def messages = GameFactory.messages;
def checks = GameFactory.checks;
def board = GameFactory.board;
println messages.welcome;
currentplayer = 1;
board.setup_board();
current_board = board.initial_board
def reader = new BufferedReader(new InputStreamReader(System.in));
def count = 0

while (count < 9) {
    messages.prompt(currentplayer);
    def input = reader.readLine().trim();
    num = checks.valid(input);
    if (num == -1 || num in checks.num_list) {
       println messages.invalid;
       continue;
    }
    checks.num_list.add(num)
    updated_board = board.update(current_board, num, currentplayer)
    board.display(updated_board)
    result = checks.check_for_victory(updated_board)
    if (result == 0){
       return
    }
    current_board = updated_board
    if (currentplayer == 1) {
          currentplayer = 2;
      } else if (currentplayer == 2) {
        currentplayer = 1;
        }
	count++;
}

println messages.draw;
