package com.example.tictactoe;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import java.io.IOException;

public class TicTacToe extends Application {

    private Label player_X, player_O;
    private int x_score=0,o_score=0;
    private Button button[][] = new Button[3][3];

    private boolean  player_turn=true;
    private BorderPane CreateContent()
    {
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(10, 50, 50, 20));

        //TITLE
        Label title = new Label("TicTacToe");
        title.setStyle("-fx-font-size:24pt; -fx-font-weight:bold;");
        title.setTextFill(Color.web("red"));
        root.setTop(title);
        root.setAlignment(title,Pos.CENTER);
        //GameBoard
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {

                Button btn = new Button();
                btn.setPrefSize(100,100);
                btn.setStyle("-fx-background-color:yellow; -fx-border-color:blue;-fx-font-size:24pt; -fx-font-weight:bold;");
                btn.setOnAction(event->click_btn(btn));
                button[i][j]=btn;
                grid.add(btn,j,i);
            }

        }
        root.setCenter(grid);

        //ScoreBoard
        HBox SB = new HBox();
        SB.setAlignment(Pos.CENTER);
        player_X = new Label("player-X : 0 ");
        player_X.setStyle("-fx-font-size:24pt; -fx-font-weight:bold;");
        player_X.setTextFill(Color.web("blue"));
        player_O = new Label("player-O : 0 ");
        player_O.setStyle("-fx-font-size:24pt; -fx-font-weight:bold;");
        player_O.setTextFill(Color.web("green"));
        SB.getChildren().addAll(player_X,player_O);
        root.setBottom(SB);
        return root;
    }

    private void click_btn(Button btn)
    {
        if(btn.getText().equals(""))
        {
            if(player_turn==true)
            {
                btn.setText("X");
            }
            else {
                btn.setText("O");
            }
            player_turn = !player_turn;
            checkWinner();

        }
    }

    private void checkWinner()
    {
        //row
          for(int row=0; row<3; row++)
          {
              if(  button[row][0].getText().equals(button[row][1].getText())
                   && button[row][1].getText().equals(button[row][2].getText())
                   && !button[row][0].getText().isEmpty() )
              {
                  String winner = button[row][0].getText();
                  displayWinner(winner);
                  updateScore(winner);
                  resetBoard();
                  return;
              }
          }


        //column
        for(int col=0; col<3; col++)
        {
            if(  button[0][col].getText().equals(button[1][col].getText())
                    && button[1][col].getText().equals(button[2][col].getText())
                    && !button[0][col].getText().isEmpty() )
            {
                String winner = button[0][col].getText();
                displayWinner(winner);
                updateScore(winner);
                resetBoard();
                return;
            }
        }

        //diagonal
        if(  button[0][0].getText().equals(button[1][1].getText())
                && button[1][1].getText().equals(button[2][2].getText())
                && !button[0][0].getText().isEmpty() )
        {
            String winner = button[0][0].getText();
            displayWinner(winner);
            updateScore(winner);
            resetBoard();
            return;
        }

        if(  button[2][0].getText().equals(button[1][1].getText())
                && button[1][1].getText().equals(button[0][2].getText())
                && !button[2][0].getText().isEmpty() )
        {
            String winner = button[2][0].getText();
            displayWinner(winner);
            updateScore(winner);
            resetBoard();
            return;
        }


        //tie

        boolean flag=true;

        for(Button row[]:button)
        {
            for(Button btn:row)
            {
                if(btn.getText().isEmpty())
                {
                    flag=false;
                    break;
                }
            }
        }
        if(flag==true)
        {
            displayTie();
            resetBoard();
        }
    }


    private void  displayWinner(String winner)
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Winner");
        alert.setContentText("Congratualations winner : "+ winner);
        alert.setHeaderText("");
        alert.showAndWait();
    }
    private void  displayTie()
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Tie");
        alert.setContentText("Game Over and its a tie");
        alert.setHeaderText("");
        alert.showAndWait();
    }
    private void updateScore(String winner)
    {
        if(winner.equals("X"))
        {
            x_score++;
            player_X.setText("player-X : "+x_score);
        }
        else {
            o_score++;
            player_O.setText("player-O : "+o_score);
        }
    }

    private void resetBoard()
    {
        for(Button row[]:button)
        {
            for(Button btn:row)
            {
                btn.setText("");
            }
        }
    }
    @Override
    public void start(Stage stage) throws IOException {

        Scene scene = new Scene(CreateContent());
        stage.setTitle("TicTacToe");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {

        launch();
    }
}