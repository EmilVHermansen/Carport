/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionLayer;

import static java.lang.Math.cos;
import static java.lang.Math.sin;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author s_ele
 */
public class BillOfMaterialsInclination
{

    private List<LineItem> billOfMaterials;
    private final int maxPoleDistance = 3000; // 3000 mm
    private final Order order;

    public BillOfMaterialsInclination(Order order)
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
        return order.getLength() * 10;
    }

    private int width()
    {
        return order.getWidth() * 10;
    }

    private boolean shed()
    {
        return (order.getShed().equals("shed") && shedLength() != 0 && shedWidth() != 0);
    }

    private int shedLength()
    {
        return order.getShedLength() * 10;
    }

    private int shedWidth()
    {
        return order.getShedWidth() * 10;
    }

    private int angle()
    {
        return order.getAngle();
    }

    private boolean fullShed()
    {
        return shedWidth() >= (width() - 300); // shedWidth == width
    }

    public List<LineItem> getBillOfMaterials()
    {
        return billOfMaterials;
    }

    // TODO: Tjek materialeId og materiale navne passer
    public void createBoM() // mastermethod
    {
        // Træ og træplader
        createPole(); // Stolper
        createWallPlate(); // Remme (carport and shed)
        createRafter(); // Spær
        createFasciaBoard(); // Sternbrædder
        createBargeAndWaterBoard(); //Vindskeder og vandbræt 
        createGableCladding(); // Gavlebeklædning
        createRoofFootBatten(); // Tagfodslægte
        createBatten(); // Lægte
        createTopBatten(); // Toplægte
        if (shed())
        {
            createZBacksideDoor();
            createShedFrame(); // skur løsholter 
            createShedCladding(); // skur beklædning
        }
        
        // Tagpakke
        createRoofTiles(); // Tagsten
        createRidgeTilesAanBrackets(); // Rygsten og rygstensbeslag
        createTopBattenHolder(); // Toplægte holder
        createRoofTileBindersAndNeckHooks(); // rygstens bindere og nakkekroge
        

        // Beslag og skruer
        if (shed())
        {
            createShedDoorKnob(); // stalddørsgreb
            createShedDoorHinge(); // Skurhængsler
            createAngleBracket(); // vinkelbeslag til skurets løsholter
        }
        createCladdingScrews(); // 4,5x50mm and 4,5x70mm screws for inner and outer cladding
        createUniversalBracket(); // universal beslag til spær på rem
        createFasciaWaterBoardScrew();  // 4,5x60mm skruer til stern og vandbræt
        createBracketScrew(); // 40x50mm skruer til montering af universalbeslag & toplægte
        createTileRidgeScrews(); // 5,0 x 100 mm skruer til taglægter
        createBoardScrewAndSquareWasher(); // bræddebolt 10x120mm og firkantskiver til rem på stolpe

    }

    private void createPole()
    {
        int length = length();
        LineItem pole = new LineItem("stk", "Stolper nedgraves 90 cm. i jord + skråstiver", idOrder(), 1);

        int poleAmount = 2; // minimum amount of poles

        if (shed())
        {
            poleAmount += 4; // mimimum addition of shedpoles (1 for the door)
            if (shedWidth() > maxPoleDistance)
            {
                poleAmount += 2;
            }
            if (shedLength() > maxPoleDistance)
            {
                poleAmount += 2;
            }
            if (shedLength() > 2 * maxPoleDistance)
            {
                poleAmount += 2;
            }
            if (shedLength() > length() - 300 - 1200)
            {
                if (fullShed())
                {
                    poleAmount -= 2;
                } else
                {
                    poleAmount -= 1;
                }

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

    private void createWallPlate() // Remme (carport and shed)
    {
        LineItem wallPlateCarport = new LineItem("stk", "Remme i sider, sadles ned i stolper", idOrder(), 2);

        if (shed())
        {
            LineItem wallPlateShed = new LineItem("stk", "Remme i sider, sadles ned i stolper (skur del, deles)", idOrder(), 2);
            wallPlateShed.setQty(1);

            if (fullShed())
            {
                wallPlateShed.setLength(shedLength() * 2 + 300);
                wallPlateCarport.setLength(length() - shedLength() - 600); // 300 on each end
                wallPlateCarport.setQty(2);
            } else
            {
                wallPlateShed.setLength(shedLength());
                wallPlateCarport.setLength(length() - 600); // 300 on each end
                wallPlateCarport.setQty(1);
                LineItem wallPlateCarport2 = new LineItem("stk", "Remme i sider, sadles ned i stolper", idOrder(), 2);
                wallPlateCarport2.setLength(length() - shedLength() - 600);
                wallPlateCarport2.setQty(1);
                billOfMaterials.add(wallPlateCarport2);
            }
            billOfMaterials.add(wallPlateShed);
        } else
        {
            wallPlateCarport.setLength(length() - 600);
            wallPlateCarport.setQty(2);
        }
        billOfMaterials.add(wallPlateCarport);
    }

    private void createRafter()
    {
        LineItem rafter = new LineItem("sæt", "byg-selv spær, skal samles", idOrder(), 23);
        int qty = 1 + ((length() - 645) / 1045); //645 is 300mmm in each end + 45 for rafter width. 
        if ((length() - 45) % 1045 > 0) //1000 is max distance between rafter + 45 of rafter width
        {
            qty++;
        }
        rafter.setQty(qty);
        billOfMaterials.add(rafter);
    }

    private void createFasciaBoard()
    {
        LineItem fasciaBoard = new LineItem("stk", "Sternbrædder til siderne", idOrder(), 22);
        
        if (length() >= 6000)
        {
        int fasciaBoardLength = length() / 2 + (300 - length() % 300);
        fasciaBoard.setQty(4);

        fasciaBoard.setLength(fasciaBoardLength);
        } else {
        int fasciaBoardLength = length() + (300 - length() % 300);

        fasciaBoard.setLength(fasciaBoardLength);
        fasciaBoard.setQty(2);
            
        }
        
        billOfMaterials.add(fasciaBoard);
    }


    private void createZBacksideDoor()
    {
        LineItem zBacksideDoor = new LineItem("stk", "Til z på bagside	af dør", idOrder(), 25);
        zBacksideDoor.setQty(1);
        zBacksideDoor.setLength(5400);
        billOfMaterials.add(zBacksideDoor);
    }

    private void createShedFrame()
    {
            LineItem shedFrameFrontBack = new LineItem("stk", "Løsholter til skur gavle", idOrder(), 8);
            LineItem shedFrameSides = new LineItem("stk", "Løsholter til skur sider", idOrder(), 8);

            int sideSpaces = shedLength() / maxPoleDistance; // spaces between the poles
            if (shedLength() % maxPoleDistance > 0)
            {
                sideSpaces++;
            }

            int sidelength = shedLength() - ((sideSpaces + 1) * 97) / sideSpaces; // shedsidelength - polewidth divided by number of spaces
            sidelength += 300 - (sidelength % 300); // make sure the length is divisible by 300 mm and make sure there is more than enough.
            shedFrameSides.setLength(sidelength);

            shedFrameSides.setQty((sideSpaces * 2 + sideSpaces * (fullShed() ? 2 : 3)));

            int frontBackSpaces = shedWidth() / maxPoleDistance; // spaces between the poles
            if (shedWidth() % maxPoleDistance > 0)
            {
                frontBackSpaces++;
            }

            int frontBacklength = (shedWidth() - ((frontBackSpaces + 1) * 97)) / frontBackSpaces; // shedsidelength - polewidth divided by number of spaces
            frontBacklength += 300 - (frontBacklength % 300); // make sure the length is divisible by 300 mm and make sure there is more than enough.
            shedFrameFrontBack.setLength(frontBacklength);

            shedFrameFrontBack.setQty(frontBackSpaces * 3 * 2);

            billOfMaterials.add(shedFrameSides);
            billOfMaterials.add(shedFrameFrontBack);
    }

    private void createShedCladding()
    {
        LineItem shedCladding = new LineItem("stk", "Til beklædning af skur 1 på 2", idOrder(), 5);
        shedCladding.setLength(2100);

        int avgBoardWidth = 74; //Based on example manual and blueprint, shed circumference divided by qty of cladding boards equals 74
        double circumference = shedLength() * 2 + shedWidth() * 2;

        int shedCladdingQty = (int) (circumference / avgBoardWidth);
        shedCladdingQty += (shedCladdingQty % avgBoardWidth == 0) ? 0 : 1;
        shedCladding.setQty(shedCladdingQty);
        billOfMaterials.add(shedCladding);
    }


    private void createBargeAndWaterBoard()
    {
        double bLength = width() / 2;
//        int bargeBoardLength = (int) ((bLength * sin(Math.toRadians((90)))) / sin(Math.toRadians(180-90-angle())));
        int bargeBoardLength = (int) (bLength / cos(Math.toRadians(angle())));
        bargeBoardLength *= 2;
        bargeBoardLength += (600 - bargeBoardLength % 300);

        LineItem bargeBoard = new LineItem("stk", "Vindskeder på rejsning", idOrder(), 22);
        bargeBoard.setLength(bargeBoardLength);
        bargeBoard.setQty(2);
        LineItem waterBoard = new LineItem("stk", "Vandbræt på vindskeder", idOrder(), 5);
        waterBoard.setLength(bargeBoardLength);
        waterBoard.setQty(2);
        billOfMaterials.add(bargeBoard);
        billOfMaterials.add(waterBoard);
    }

    private void createGableCladding()
    {
        LineItem gableCladding = new LineItem("stk", "Til beklædning gavle 1 på 2", idOrder(), 5);
        gableCladding.setLength(2400);
        
        int avgBoardCoverage = 190; //Based on example manual and blueprint, carport width / number of claddingboards.

        int gableCladdingQty = (width() * 2) / avgBoardCoverage;
        gableCladdingQty += (gableCladdingQty % avgBoardCoverage == 0) ? 0 : 1;
        gableCladding.setQty(gableCladdingQty);
        billOfMaterials.add(gableCladding);
    }

    private void createRoofFootBatten()
    {
        LineItem roofFootBatten = new LineItem("stk", "Til montering oven på tagfodslægte", idOrder(), 24);
        if (length() >= 600)
        {
            roofFootBatten.setQty(3);
            int roofFootBattenLength = length() * 2 / 3;
            roofFootBatten.setLength(roofFootBattenLength + (300 - roofFootBattenLength % 300));
        } else
        {
            roofFootBatten.setQty(2);
            roofFootBatten.setLength(length() + (300 - length() % 300));
        }
        billOfMaterials.add(roofFootBatten);
    }

    private void createBatten()
    {
        LineItem batten = new LineItem("stk", "Til montering på spær, 7 rækker på hver side, skiftevis en hel og en halv lægte", idOrder(), 25);
        if (length() >= 600)
        {
            batten.setQty(21); // 14 rows 1,5 on each row
            int battenLength = length() * 2 / 3;
            batten.setLength(battenLength + (300 - battenLength % 300));
        } else
        {
            batten.setQty(14); // 14 rows 1 on each row
            batten.setLength(length() + (300 - length() % 300));
        }
        billOfMaterials.add(batten);
    }

    private void createTopBatten()
    {
        LineItem topBatten = new LineItem("stk", "Toplægte på montering af rygsten, lægges i toplægte holder", idOrder(), 25);
        if (length() >= 600)
        {
            topBatten.setQty(2); 
            int battenLength = length() / 2;
            topBatten.setLength(battenLength + (300 - length() % 300));
        } else
        {
            topBatten.setQty(1); 
            topBatten.setLength(length() + (300 - length() % 300));
        }
        billOfMaterials.add(topBatten);
        
    }
    
    private void createRoofTiles()
    {
        LineItem roofTiles = new LineItem("stk", "Monteres på taglægter, 6 rækker af 24 sten på hver side af taget", idOrder(), 26);
        
        int tileWidthCoverage = 300; //Based on BoM example manual, carport width / number of tile amount per row.
        
        roofTiles.setQty((length() / tileWidthCoverage + 1) * 12);
        
        billOfMaterials.add(roofTiles);
    }

    private void createRidgeTilesAanBrackets()
    {
        LineItem ridgeTiles = new LineItem("stk", "Monteres på toplægte med medfølgende beslag, se tagstensvejledning", idOrder(), 27);
        LineItem ridgeTileBrackets = new LineItem("stk", "Til montering af rygsten", idOrder(), 28);
        
        int tileWidthCoverage = 345; //Based on BoM example manual, carport width / number of ridgetiles.
        int qty = (length() / tileWidthCoverage + 1);
        
        ridgeTiles.setQty(qty);
        ridgeTileBrackets.setQty(qty);
        
        billOfMaterials.add(ridgeTiles);
        billOfMaterials.add(ridgeTileBrackets);
    }

    private void createTopBattenHolder()
    {
        LineItem topBattenHolder = new LineItem("stk", "Monteres på toppen af spærret", idOrder(), 29);
        
        int qty = 1 + ((length() - 645) / 1045); //645 is 300mmm in each end + 45 for rafter width. 
        if ((length() - 45) % 1045 > 0) //1000 is max distance between rafter + 45 of rafter width
        {
            qty++;
        }
        
        topBattenHolder.setQty(qty);
        billOfMaterials.add(topBattenHolder);
    }

    private void createRoofTileBindersAndNeckHooks()
    {
        
        LineItem RoofTileBindersAndNeckHooks = new LineItem("pk.", "Til montering af tagsten, alle ydersten. Hver anden fastgøres", idOrder(), 30);
        
        RoofTileBindersAndNeckHooks.setQty(2);
        
        billOfMaterials.add(RoofTileBindersAndNeckHooks);
    }
    
    private void createShedDoorKnob()
    {
        LineItem shedDoorKnob = new LineItem("sæt", "Til lås på dør i skur", idOrder(), 10);
        shedDoorKnob.setQty(1);
        billOfMaterials.add(shedDoorKnob);
    }

    private void createShedDoorHinge()
    {
        LineItem shedDoorHinge = new LineItem("stk", "Til skurdør", idOrder(), 11);
        shedDoorHinge.setQty(2);
        billOfMaterials.add(shedDoorHinge);
    }

    private void createCladdingScrews()
    {
        LineItem outerScrew = new LineItem("stk", "Til montering af yderste beklædning", idOrder(), 19);
        LineItem innerScrew = new LineItem("stk", "Til montering af inderste beklædning", idOrder(), 20);
        
        int innerScrewQty = 0;
        int outerScrewQty = 0;
        
        if (shed())
        {
        int avgBoardWidth = 74; //Based on example manual and blueprint, shed circumference divided by qty of cladding boards equals 74
        double circumference = shedLength() * 2 + shedWidth() * 2;

        int shedCladdingQty = (int) (circumference / avgBoardWidth);
        shedCladdingQty += (shedCladdingQty % avgBoardWidth == 0) ? 0 : 1;
        
        outerScrewQty += ((shedCladdingQty / 2) + (shedCladdingQty % 2)) * 8;

        innerScrewQty += ((shedCladdingQty / 2) + (shedCladdingQty % 2)) * 6;
        }
        
        int avgBoardCoverage = 190; //Based on example manual and blueprint, carport width / number of claddingboards.

        int gableCladdingQty = (width() * 2) / avgBoardCoverage;
        gableCladdingQty += (gableCladdingQty % avgBoardCoverage == 0) ? 0 : 1;


        innerScrewQty += ((gableCladdingQty / 2) + (gableCladdingQty % 2)) * 2;
        outerScrewQty += ((gableCladdingQty / 2) + (gableCladdingQty % 2)) * 4;
        
        outerScrewQty /= 400; // 400 screws per pack
        innerScrewQty /= 300; // 300 screws per pack
        
        outerScrew.setQty(outerScrewQty);
        innerScrew.setQty(innerScrewQty);

        billOfMaterials.add(outerScrew);
        billOfMaterials.add(innerScrew);
    }

    private void createAngleBracket()
    {
            LineItem angleBracket = new LineItem("stk", "Til montering af løsholter i skur", idOrder(), 21);
            int angleBracketQty;

            int sideSpaces = shedLength() / maxPoleDistance; // spaces between the poles
            if (shedLength() % maxPoleDistance > 0)
            {
                sideSpaces++;
            }

            angleBracketQty = sideSpaces * 2 + sideSpaces * (fullShed() ? 2 : 3);

            int frontBackSpaces = shedWidth() / maxPoleDistance; // spaces between the poles
            if (shedWidth() % maxPoleDistance > 0)
            {
                frontBackSpaces++;
            }

            angleBracketQty += frontBackSpaces * 3 * 2;
            angleBracketQty *= 2;

            angleBracket.setQty(angleBracketQty);

            billOfMaterials.add(angleBracket);
    }

    private void createUniversalBracket()
    {
        LineItem universalHingeLeft = new LineItem("stk", "Til montering af spær på rem", idOrder(), 12);
        LineItem universalHingeRight = new LineItem("stk", "Til	montering af spær på rem", idOrder(), 13);

        int qty = 1 + ((length() - 645) / 1045); //645 is 300mmm in each end + 45 for rafter width. 
        if ((length() - 45) % 1045 > 0) //1000 is max distance between rafter + 45 of rafter width
        {
            qty++;
        }
        universalHingeLeft.setQty(qty);
        universalHingeRight.setQty(qty);
        billOfMaterials.add(universalHingeLeft);
        billOfMaterials.add(universalHingeRight);
        
    }

    private void createFasciaWaterBoardScrew() // 
    {
        LineItem FasciaWaterBoardScrew = new LineItem("pk.", "Til montering af stern, vindskeder, vindkryds og vandbræt", idOrder(), 15);
        FasciaWaterBoardScrew.setQty(1);
        billOfMaterials.add(FasciaWaterBoardScrew);
    }

    private void createBracketScrew()
    {
        LineItem bracketScrew = new LineItem("pk.", "Til montering af universalbeslag & toplægte", idOrder(), 16);
        int rafterQty = 1 + ((length() - 645) / 1045); //645 is 300mmm in each end + 45 for rafter width. 
        if ((length() - 45) % 1045 > 0) //1000 is max distance between rafter + 45 of rafter width
        {
            rafterQty++;
        }
            /* We multiply with 2 because there are 2 brackets per rafter
           We multiply with 20 because we assume there are 20 screws per bracket
           We add 5 for the topbatten
             */
        int totalScrews = rafterQty * 2 * 20 + 5;
        int qty = (totalScrews / 250) + ((totalScrews % 250 == 0) ? 0 : 1);
        bracketScrew.setQty(qty);
        billOfMaterials.add(bracketScrew);
    }

    private void createTileRidgeScrews()
    {
        LineItem tileRidgeScrew = new LineItem("stk", "Til taglægter", idOrder(), 31);
        tileRidgeScrew.setQty(2);
        billOfMaterials.add(tileRidgeScrew);
    }
    
    private void createBoardScrewAndSquareWasher()
    {
        int length = length();
        LineItem boardScrew = new LineItem("stk", "Montering af rem på stolper", idOrder(), 17);
        LineItem squareWasher = new LineItem("stk", "Montering af rem på stolper", idOrder(), 18);

        int poleAmount = 2; // minimum amount of poles

        poleAmount += ((length() - shedLength()) / maxPoleDistance * 2);

        if (length % maxPoleDistance > 1200)
        {
            poleAmount += 2;
        }

        boardScrew.setQty(poleAmount * 5); // 5 is calculated from the example manual: 20 qty divided by 4 poles (non-shed)
        squareWasher.setQty(poleAmount * 5);

        billOfMaterials.add(boardScrew);
        billOfMaterials.add(squareWasher);
    }

}//CLASS
