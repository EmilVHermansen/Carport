/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionLayer;

import java.util.ArrayList;
import java.util.List;

/**
 * ALL UNITS ARE IN MILIMETER
 *
 * @author s_ele
 */
public class BillOfMaterials
{

    private List<LineItem> billOfMaterials;
    private final int maxPoleDistance = 3000; // 3000 mm
    private final Order order;

    public BillOfMaterials(Order order)
    {
        this.billOfMaterials = new ArrayList();
        this.order = order;
    }

    private int idOrder()
    {
        return order.getIdOrder();
    }

    private int length()
    {
        return order.getLength();
    }

    private int width()
    {
        return order.getWidth();
    }

    private boolean shed()
    {
        return order.getShed().equals("shed");
    }

    private boolean fullShed()
    {
        return order.getShedWidth() == (order.getWidth() - 700); // shedWidth == width
    }

    private int shedLength()
    {
        return order.getShedLength();
    }

    private int shedWidth()
    {
        return order.getShedWidth();
    }

    public void createBoM() // mastermethod
    {
        // Træ og træplader
        createPole(); // Stolper
        createWallPlate(); // Remme (carport and shed)
        createRafter(); // Spær
        createFasciaAndWaterBoard(); // Stern og vandbrædt (over and under, front/back and sides)(front and sides)
        createRoofPlate(); // Tagplader
        createZBacksideDoor();
        createShedFrame(); // skur løsholter
        createShedCladding(); // skur beklædning

        // Beslag og skruer
        createPerforatedBand(); // hulbånd
        createShedDoorKnob(); // stalddørsgreb
        createShedDoorHinge(); // Skurhængsler
        createUniversalBracket(); // universal beslag til spær på rem
        createBottomScrew(); // bundskruer til tagplader
        createFasciaWaterBoardScrew();  // 4,5x60mm skruer til stern og vandbræt
        createBracketScrew(); // 40x50mm skruer til beslag
        createBoardScrewAndSquareWasher(); // bræddebolt 10x120mm og firkantskiver til rem på stolpe
        createShedCladdingScrews(); // 4,5x50mm and 4,5x70mm screws for inner and outer cladding
        createAngleBracket(); // vinkelbeslag til skurets løsholter

    }

    public List<LineItem> getBillOfMaterials()
    {
        return billOfMaterials;
    }

    // TODO Shed can not be more than 3 m long (Need change)
    void createPole() // Stolper  
    {

        int length = length();
        LineItem pole = new LineItem("stk", "Stolper nedgraves 90 cm. i jord", idOrder(), 1);

        int poleAmount = 2; // minimum amount of poles

        if (shed())
        {
            poleAmount += 4; // mimimum addition of shedpoles (1 for the door)
            if (shedWidth() > maxPoleDistance)
            {
                poleAmount += 2;
            }
            if (fullShed())
            {
                poleAmount -= 1;
                length -= shedLength();
            }
        }

        poleAmount += (length / maxPoleDistance * 2);

        if (length % maxPoleDistance > 1200)
        {
            poleAmount += 2;
        }
        pole.setQty(poleAmount);
        pole.setLength(3000);
        billOfMaterials.add(pole);

    }

    void createWallPlate() // Remme (carport and shed)
    {
        LineItem wallPlateCarport = new LineItem("stk", "Remme i sider, sadles ned i stolper", idOrder(), 2);

        if (shed())
        {
            LineItem wallPlateShed = new LineItem("stk", "Remme i sider, sadles ned i stolper (skur del, deles)", idOrder(), 2);
            wallPlateShed.setLength(shedLength() * 2);
            wallPlateShed.setQty(1);
            billOfMaterials.add(wallPlateShed);

            if (fullShed())
            {
                wallPlateCarport.setLength(length() - shedLength());
                wallPlateCarport.setQty(2);
            } else
            {
                wallPlateCarport.setLength(length());
                wallPlateCarport.setQty(1);

            }
        } else
        {
            wallPlateCarport.setLength(length());
            wallPlateCarport.setQty(2);
        }
        billOfMaterials.add(wallPlateCarport);
    }

    void createRafter() // Spær
    {
        LineItem rafter = new LineItem("stk", "Spær, monteres på rem", idOrder(), 3);
        rafter.setLength(width());
        int qty = 1 + ((length() - 45) / 600);
        if ((length() - 45) % 600 > 0)
        {
            qty++;
        }
        rafter.setQty(qty);
        billOfMaterials.add(rafter);
    }

    void createFasciaAndWaterBoard() // Stern (over and under, front/back and sides) og vandbrædt (front and sides)
    {
        LineItem fasciaBoardUnderFrontBack = new LineItem("stk", "understernbrædder til for & bag ende", idOrder(), 4);
        fasciaBoardUnderFrontBack.setQty(4);

        LineItem fasciaBoardUnderSides = new LineItem("stk", "understernbrædder til siderne", idOrder(), 4);
        fasciaBoardUnderSides.setQty(4);

        LineItem fasciaBoardFront = new LineItem("stk", "oversternbrædder til forenden", idOrder(), 5);
        fasciaBoardFront.setQty(2);

        LineItem fasciaBoardSides = new LineItem("stk", "oversternbrædder til siderne", idOrder(), 5);
        fasciaBoardSides.setQty(4);

        LineItem waterBoardFront = new LineItem("stk", "vandbrædt på stern i forende", idOrder(), 6);
        waterBoardFront.setQty(2);

        LineItem waterBoardSides = new LineItem("stk", "vandbrædt på stern i sider", idOrder(), 6);
        waterBoardSides.setQty(4);

        int frontBackLength = width() - (width() % 300);
        if (width() % 300 > 0)
        {
            frontBackLength += 300;
        }
        fasciaBoardUnderFrontBack.setLength(frontBackLength);
        fasciaBoardFront.setLength(frontBackLength);
        waterBoardFront.setLength(frontBackLength);

        int sideLength = length() - (length() % 300);
        if (length() % 300 != 0)
        {
            sideLength += 300;
        }

        fasciaBoardUnderSides.setLength(sideLength);
        fasciaBoardSides.setLength(sideLength);
        waterBoardSides.setLength(sideLength);

        billOfMaterials.add(fasciaBoardUnderFrontBack);
        billOfMaterials.add(fasciaBoardUnderSides);
        billOfMaterials.add(fasciaBoardFront);
        billOfMaterials.add(fasciaBoardSides);
        billOfMaterials.add(waterBoardFront);
        billOfMaterials.add(waterBoardSides);

    }

    void createRoofPlate() // Tagplader
    {
        LineItem roofPlate1 = new LineItem("stk", "tagplader monteres på spær", idOrder(), 7);
        int qty = width() / 1040; //width of a roofplate on Fogs website

        if (width() % 1040 > 0)
        {
            qty++;
        }

        roofPlate1.setQty(qty);

        if (length() > 6000)
        {
            roofPlate1.setLength(6000);
            LineItem roofPlate2 = new LineItem("stk", "tagplader monteres på spær", idOrder(), 7);
            roofPlate2.setQty(qty);
            int length2 = length() - 6000;
            if (length2 <= 2400)
            {
                roofPlate2.setLength(2400);
            }
//            else if (length2 <= 3000) {
//                roofPlate2.setLength(3000);
//            } else if (length2 <= 3600) {
//                roofPlate2.setLength(3600);
//            } else if (length2 <= 4200) {
//                roofPlate2.setLength(4200);
//            } else if (length2 <= 4800) {
//                roofPlate2.setLength(4800);
//            } else if (length2 <= 6000) {
//                roofPlate2.setLength(6000);
            billOfMaterials.add(roofPlate1);
            billOfMaterials.add(roofPlate2);
        } else
        {
            int length = length();
            if (length <= 2400)
            {
                roofPlate1.setLength(2400);
            } else if (length <= 3000)
            {
                roofPlate1.setLength(3000);
            } else if (length <= 3600)
            {
                roofPlate1.setLength(3600);
            } else if (length <= 4200)
            {
                roofPlate1.setLength(4200);
            } else if (length <= 4800)
            {
                roofPlate1.setLength(4800);
            } else if (length <= 6000)
            {
                roofPlate1.setLength(6000);
            }
            billOfMaterials.add(roofPlate1);
        }
    }

    void createZBacksideDoor()
    {
        LineItem zBacksideDoor = new LineItem("stk", "til z på bagside	af dør", idOrder(), 8);
        zBacksideDoor.setQty(1);
        zBacksideDoor.setLength(420);
        billOfMaterials.add(zBacksideDoor);
    }

    void createShedFrame() // skur løsholter
    {
        if (shed())
        {
            LineItem shedFrameFrontBack = new LineItem("stk", "løsholter til skur gavle", idOrder(), 9);
            LineItem shedFrameSides = new LineItem("stk", "løsholter til skur sider", idOrder(), 9);

            int sideSpaces = shedLength() / maxPoleDistance; // spaces between the poles
            if (shedLength() % maxPoleDistance > 0)
            {
                sideSpaces++;
            }

            int sidelength = shedLength() - ((sideSpaces + 1) * 97) / sideSpaces; // shedsidelength - polewidth divided by number of spaces
            sidelength += 300 - (sidelength % 300); // make sure the length is divisible by 300 mm and make sure there is more than enough.
            shedFrameSides.setLength(sidelength);

            shedFrameSides.setQty(sideSpaces * 2 + (fullShed() ? 0 : 1));

            int frontBackSpaces = shedWidth() / maxPoleDistance; // spaces between the poles
            if (shedWidth() % maxPoleDistance > 0)
            {
                frontBackSpaces++;
            }

            int frontBacklength = shedWidth() - ((frontBackSpaces + 1) * 97) / frontBackSpaces; // shedsidelength - polewidth divided by number of spaces
            frontBacklength += 300 - (frontBacklength % 300); // make sure the length is divisible by 300 mm and make sure there is more than enough.
            shedFrameFrontBack.setLength(frontBacklength);

            shedFrameFrontBack.setQty(frontBackSpaces * 3);

            billOfMaterials.add(shedFrameSides);
            billOfMaterials.add(shedFrameFrontBack);
        }
    }

    void createShedCladding() // skur beklædning
    {
        LineItem shedCladding = new LineItem("stk", "til beklædning af skur 1 på 2", idOrder(), 10);
        shedCladding.setLength(2100);
        
        double avgBoardWidth = 7.4; //Based on example manual and blueprint, shed circumference divided by qty of cladding boards equals 7.4
        double circumference = shedLength() * 2 + shedWidth() * 2;
        

        int shedCladdingQty = (int) (circumference / avgBoardWidth);
        shedCladdingQty += (shedCladdingQty % avgBoardWidth == 0) ? 0 : 1;
        shedCladding.setQty(shedCladdingQty);
        billOfMaterials.add(shedCladding);
    }

    void createPerforatedBand() // hulbånd
    {
        int length;
        int width;
        int diagonal;

        int divSubLength = length() / 600;
        if (length() % 600 > 0)
        {
            divSubLength++;
        }

        int sublength1 = length() / divSubLength;

        int sublength2;

        if (shed())
        {
            sublength2 = shedLength() + 150;
        } else
        {
            sublength2 = sublength1;
        }

        //Calculation
        length = length() - sublength1 - sublength2;
        width = width() - (35 * 2);
        diagonal = (int) Math.sqrt(Math.pow(length, 2) + Math.pow(width, 2)); //Pythagoras

        int perforatedBandQty = 2;
        if (diagonal > 10)
        {
            perforatedBandQty *= 2;
        }

        LineItem perforatedBand = new LineItem("stk", "Til vindkryds på spær", idOrder(), 11);

        perforatedBand.setQty(perforatedBandQty);

        billOfMaterials.add(perforatedBand);
    }

    void createShedDoorKnob()
    {
        LineItem shedDoorKnop = new LineItem("sæt", "Til lås på dør i skur", idOrder(), 12);
        shedDoorKnop.setQty(1);
        billOfMaterials.add(shedDoorKnop);
    }

    void createShedDoorHinge()
    {
        LineItem shedDoorHinge = new LineItem("stk", "Til skurdør", idOrder(), 13);
        shedDoorHinge.setQty(2);
        billOfMaterials.add(shedDoorHinge);
    }

    void createUniversalBracket()
    {
        LineItem universalHingeLeft = new LineItem("stk", "Til montering af spær på rem", idOrder(), 14);
        LineItem universalHingeRight = new LineItem("stk", "Til	montering af spær på rem", idOrder(), 15);

        int qty = 1 + ((length() - 45) / 600);
        if ((length() - 45) % 600 > 0)
        {
            qty++;
        }
        universalHingeLeft.setQty(qty);
        universalHingeRight.setQty(qty);
        billOfMaterials.add(universalHingeLeft);
        billOfMaterials.add(universalHingeRight);
    }

    private void createBottomScrew()
    {
        LineItem bottomScrew = new LineItem("Pakke", "Skruer til tagplader", idOrder(), 16);
        //based on existing example of BoM and manual
        int length = 7800;
        int width = 6000;
        double amountOfTotalScrews = 600;

        double screwsPerSqrMilimeter = amountOfTotalScrews / (length * width);
        int totalScrews = (int) (length * width * screwsPerSqrMilimeter);
        int qty = (totalScrews / 200) + ((totalScrews % 200 == 0) ? 0 : 1);

        bottomScrew.setQty(qty);
        billOfMaterials.add(bottomScrew);
    }

    private void createFasciaWaterBoardScrew()
    {
        LineItem FasciaWaterBoardScrew = new LineItem("Pakke", "Til montering af stern & vandbræt", idOrder(), 17);
        FasciaWaterBoardScrew.setQty(1);
        billOfMaterials.add(FasciaWaterBoardScrew);

    }

    private void createBracketScrew()
    {
        LineItem bracketScrew = new LineItem("Pakke", "Til montering af universalbeslag & hulbånd", idOrder(), 18);
        int rafterQty = 1 + ((length() - 45) / 600);
        if ((length() - 45) % 600 > 0)
        {
            rafterQty++;

            /* We multiply with 2 because there are 2 brackets per rafter
           We multiply with 20 because we assume there is 20 screws per bracket
           We add 5 for the perforated band
             */
        }
        int totalScrews = rafterQty * 2 * 20 + 5;
        int qty = (totalScrews / 250) + ((totalScrews % 250 == 0) ? 0 : 1);
        bracketScrew.setQty(qty);
        billOfMaterials.add(bracketScrew);
    }

    private void createBoardScrewAndSquareWasher()
    {
        int length = length();
        LineItem boardScrew = new LineItem("stk", "Montering af rem på stolper", idOrder(), 19);
        LineItem squareWasher = new LineItem("stk", "Montering af rem på stolper", idOrder(), 20);

        int poleAmount = 2; // minimum amount of poles

        poleAmount += (length / maxPoleDistance * 2);

        if (length % maxPoleDistance > 1200)
        {
            poleAmount += 2;
        }
        
        boardScrew.setQty(poleAmount*3);
        squareWasher.setQty(poleAmount*2);
        
        billOfMaterials.add(boardScrew);
        billOfMaterials.add(squareWasher);
    }

    private void createShedCladdingScrews()
    {
        LineItem outerScrew = new LineItem("stk", "Til montering af yderste beklædning", idOrder(), 21);
        LineItem innerScrew = new LineItem("stk", "Til montering af inderste beklædning", idOrder(), 22);
        
        double avgBoardWidth = 7.4; //Based on example manual and blueprint, shed circumference divided by qty of cladding boards equals 7.4
        double circumference = shedLength() * 2 + shedWidth() * 2;
        

        int shedCladdingQty = (int) (circumference / avgBoardWidth);
        shedCladdingQty += (shedCladdingQty % avgBoardWidth == 0) ? 0 : 1;
        
        int outerScrewQty = (shedCladdingQty / 2) + (shedCladdingQty % 2) * 8;
        outerScrew.setQty(outerScrewQty);
        
        int innerScrewQty = (shedCladdingQty / 2) + (shedCladdingQty % 2) * 6;
        innerScrew.setQty(innerScrewQty);
        
        billOfMaterials.add(outerScrew);
        billOfMaterials.add(innerScrew);
    }

    private void createAngleBracket()
    {
        if (shed())
        {
            LineItem angleBracket = new LineItem("stk", "Til montering af løsholter i skur", idOrder(), 23);
            int angleBracketQty;
            
            int sideSpaces = shedLength() / maxPoleDistance; // spaces between the poles
            if (shedLength() % maxPoleDistance > 0)
            {
                sideSpaces++;
            }
            
            angleBracketQty = sideSpaces * 2 + (fullShed() ? 0 : 1);

            int frontBackSpaces = shedWidth() / maxPoleDistance; // spaces between the poles
            if (shedWidth() % maxPoleDistance > 0)
            {
                frontBackSpaces++;
            }


            angleBracketQty += frontBackSpaces * 3;
            angleBracketQty *= 2;
            
            angleBracket.setQty(angleBracketQty);

            billOfMaterials.add(angleBracket);
        }
    }

}//CLASS
