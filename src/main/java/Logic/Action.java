package Logic;

import Entity.DiceCup;
import Entity.Fields.Brewery;
import Entity.Fields.Ferry;
import Entity.Fields.Field;
import Entity.Fields.Street;
import Entity.GameBoard;
import Entity.Player;
import Entity.PlayerList;

public class Action {
    private LogicList ll = new LogicList();
    private DiceCup dc =  DiceCup.getINSTANCE();
    private PlayerMove mp = new PlayerMove();
    private PropertyOwnership po = new PropertyOwnership();
    private GameBoard gb = GameBoard.getInstance();
    private Field[] fl = gb.getFields();
    private PlayerList pl = PlayerList.getInstance();
    private Taxes taxes = new Taxes();
    private PropertyRent pr = new PropertyRent(gb);
    private PropertyFields pf = new PropertyFields();
    private JailLogic jl = new JailLogic();
    private String option;



    public String decideAction (String action, Player p) {

        option = "";

        switch (action) {

            case "Rul":
                dc.testRoll(); //Test

                if (jl.checkNumDoubles(p, dc.isFaceValueSame()))
                    option = "3Doubles";
                else {
                    mp.movePlayer(p, gb);
                    option += ll.checkFieldType(p.getFieldIndex(), p);
                }
                break;

            case "Køb":
                po.buyField(p, fl);
                checkForExtra(p);
                break;

            case "Køb ikke":
            case "Ok":
                checkForExtra(p);
                break;

            case "Betal 2000kr.-":
                taxes.stateTax(p);
                checkForExtra(p);
                break;

            case "Betal 4000kr.-":
                taxes.incomeTaxCash(p);
                checkForExtra(p);
                break;

            case "Betal 10%":
                taxes.incomeTaxPercentage(p);
                checkForExtra(p);
                break;

            case "Betal":
                payRent(p);
                checkForExtra(p);
                break;

            case "Betal 1000kr.-":
                jl.payOutOfJail(p);
                option = "Roll";
                break;

            case "Prøv at slå par":
                dc.testRoll(); // test
                if (dc.isFaceValueSame()) {
                    p.setInJail(false);
                    option = "Free";
                    p.setRoundsInJail(0);
                } else
                    option = "NotFree";
                break;

            case "Ryk i fængsel":
                p.setFieldIndex(10);
                option = "End";
                break;


        }


        return option;
    }







    private void checkForExtra(Player p) {
        if (p.isAbleToBuyHouses())
            option += "House";

        if (dc.isFaceValueSame())
            option += "RollAgain";

        if (!(dc.isFaceValueSame() && !(p.isAbleToBuyHouses())))
            option += "End";
    }

    private void payRent(Player p) {
        int owner = pf.whoseProberty(p.getFieldIndex());
        if (fl[p.getFieldIndex()] instanceof Street)
            pr.payRentStreet(owner, p, pl.getPlayers(), fl);

        if (fl[p.getFieldIndex()] instanceof Ferry)
            pr.payRentFerry(owner, p, pl.getPlayers(), fl);

        if (fl[p.getFieldIndex()] instanceof Brewery)
            pr.payRentBrewery(owner, p, pl.getPlayers(), fl);
    }

    public void jailCountUp(Player p) {
        if (p.isInJail())
            p.addRoundInJail();
    }

    public boolean threeRoundsInJail(Player p) {
        if (p.getRoundsInJail() == 3) {
            p.addMoney(-1000);
            p.setInJail(false);
            return false;
        } else
            return true;
    }

}
