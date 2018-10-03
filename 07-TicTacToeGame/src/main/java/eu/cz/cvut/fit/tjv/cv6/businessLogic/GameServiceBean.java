/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.cz.cvut.fit.tjv.cv6.businessLogic;

import eu.cz.cvut.fit.tjv.cv6.tictactoe_cli.game.GameController;
import eu.cz.cvut.fit.tjv.cv6.tictactoe_cli.game.GameControllerCLI;
import eu.cz.cvut.fit.tjv.cv6.tictactoe_cli.game.ServiceCrate;
import eu.cz.cvut.fit.tjv.cv6.tictactoe_cli.player.Player;
import javax.ejb.Singleton;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author jpavlicek
 */
@Singleton
public class GameServiceBean {
   private GameController gameController;
   private Player player1;
   private Player player2;   
   private ServiceCrate sc; 

   
   //This method sets gameController from first page index.html 
   public void intGame(HttpServletRequest request){
      sc = new ServiceCrate();
      sc.setSize(new Byte(request.getParameter("GridSize")));
      sc.setnToWin(new Byte(request.getParameter("GameNtoWin")));
      player1 = new Player(request.getParameter("Player1"));
      player2 = new Player(request.getParameter("Player2"));
      gameController  = new GameControllerCLI(sc, player1, player2); 
   }
    
   //This method controlls moves in the game dashboard
   public String nextMove(HttpServletRequest request) {
       String moveCorrector="";
       sc.setX(new Byte(request.getParameter("X")));
       sc.setY(new Byte(request.getParameter("Y")));
       moveCorrector=gameController.nextMove();
       
       //IF move is OK, sysemt show grid, else info about BAD move or game finish
       if(moveCorrector.equalsIgnoreCase("OK")){
       return  gameController.getGameView().displayGrid();
       }
       
       else return gameController.getGameView().displayGrid()+"<br>"+moveCorrector;
   }
    
    
    
    
    
    
}
