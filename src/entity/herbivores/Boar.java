package entity.herbivores;

import entity.Cell;
import entity.Plant;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

public class Boar extends Herbivores {
    public static int newBoar = 0;
    public static int deathBoar = 0;
    public Boar() {
        weight = 400_000;
        stepDeath = 0;
        name = Integer.toString(newBoar);
        // кабаны слишком много едят, потому что получается что один кабан есть половину популяции мышей или гусениц
        // пусть кабан есть 5% от массы собственного веса 20 кг == 20_000 грамм
        satiety = 20_000;
        isEat = true;
        isMove = false;
        isReproduce = false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Boar boar = (Boar) o;
        return Objects.equals(name, boar.name);
    }
    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "Boar{" +
                "weight=" + weight +
                ", satiety=" + satiety +
                ", stepDeath=" + stepDeath +
                '}';
    }

    public static void life(Cell[][] cells, int i, int j){
        ArrayList<Boar> boars = cells[i][j].getBoar();
        ArrayList<Boar> newBoar = new ArrayList<>();
        ArrayList<Boar> arrayListBoarNeedToDelete = new ArrayList<>();
        for (int k = 0; k < boars.size(); k++) {
            Boar boar = boars.get(k);

            //выбираем что кушаем
            //0-caterpillar
            //1-plant
            //2-mouse
            int randomFood = ThreadLocalRandom.current().nextInt(3);
            if(randomFood == 0){
                ArrayList<Caterpillar> caterpillars = cells[i][j].getCaterpillar();
                boar.eat(caterpillars);
            }else if(randomFood == 1){
                List<Plant> plants = cells[i][j].getSynchronizedPlant();
                boar.eat(plants);
            }else if(randomFood == 2){
                ArrayList<Mouse> Mouses = cells[i][j].getMouse();
                boar.eat(Mouses);
            }
            //размножаемся
            newBoar.addAll(boar.reproduce(cells,i,j));
//
            //передвижение
            Boar boarMove = boar.move(cells,i,j);
            if(boarMove != null){
                arrayListBoarNeedToDelete.add(boarMove);
            }
//
        }
        boars.addAll(newBoar);
        for (Boar boar: arrayListBoarNeedToDelete) {
            boars.remove(boar);
        }
//
        int needToKill =0;
        Iterator<Boar> iterator = boars.iterator();
        // уменьшаю количество кабанов с 50 до 10
        if(boars.size()>10){
            needToKill = Math.abs(boars.size()-10);
            while (needToKill>0){
                iterator.next();
                iterator.remove();
                Boar.deathBoar++;
                needToKill--;
            }
        }else{
            while (iterator.hasNext()){
                Boar boar = iterator.next();
                if(boar.stepDeath==2){
                    iterator.remove();
                    Boar.deathBoar++;
                }
            }
        }
    }

    public void eat(List<Plant> plants){
        //кабаны едят половина растений на карте, если он даже немного поел ставлю ему поленое насыщение иноже он вымирает
        //кабан есть 5 % от своего веса
        if(this.isEat == false && this.isReproduce == false  && this.isMove == false) {
            Iterator<Plant> iterator = plants.iterator();
            int needToEat = 20;
            while (iterator.hasNext()){
                iterator.next();
                iterator.remove();
                Plant.deathPlants++;
                needToEat--;
                if(needToEat ==0)break;
            }
            this.satiety=20_000;


            this.stepDeath =0;
            this.isEat = true;
        }else{
            this.isEat = false;
            this.satiety-=10_000;
            if(this.satiety<0)stepDeath++;
        }
    }

    public <T> void  eat(ArrayList<T> animal){
        Object object = null;
        //System.out.println("eat Animal");
        if(animal.size()>0){
            object = animal.get(0);
            if(object instanceof Caterpillar){
                //гусеницы питательние чем трава, получается 1 гусеница = 100 грамм
                //чтобы кабан наелся он должен съесть 500 гусениц
                // если гусеницы закончились, все равно ставим, что кабан наелся
                int chanceToEat = ThreadLocalRandom.current().nextInt(100);
                chanceToEat++;
                if(this.isEat == false && this.isReproduce == false  && this.isMove == false && (chanceToEat<90)) {
                    Iterator<T> iterator = animal.iterator();
                    int needToEat = 200;
                    while (iterator.hasNext()){
                        iterator.next();
                        iterator.remove();
                        Caterpillar.deathGaterpillar++;
                        needToEat--;
                        if(needToEat ==0)break;
                    }
                    this.satiety = 20_000;
                    this.stepDeath = 0;
                    this.isEat = true;
                }else{
                    this.isEat = false;
                    this.satiety-=10_000;
                    if(this.satiety<0)stepDeath++;
                }
            }else if(object instanceof Mouse){
                //мышь питательние чем трава, получается 1 мышь = 500 грамм
                int chanceToEat = ThreadLocalRandom.current().nextInt(100);
                int needToEat = 40;
                chanceToEat++;
                if(this.isEat == false && this.isReproduce == false  && this.isMove == false && (chanceToEat<50)) {
                    Iterator<T> iterator = animal.iterator();
                    while (iterator.hasNext()){
                        iterator.next();
                        iterator.remove();
                        Mouse.deathMouse++;
                        needToEat--;
                        if(needToEat ==0)break;
                    }
                    this.satiety = 20_000;
                    this.stepDeath = 0;
                    this.isEat = true;
                }else{
                    this.isEat = false;
                    this.satiety-=10_000;
                    if(this.satiety<0)stepDeath++;
                }
            }
        }else{
            this.isEat = false;
            this.satiety-=10_000;
            if(this.satiety<0)stepDeath++;
        }


        //ест все растения, ставим ему полное насыщение

    }
    @Override
    public ArrayList<Boar> reproduce(Cell[][] cells, int i, int j) {
        ArrayList<Boar> newBoar= new ArrayList<>();
        int randomLengthBoar = ThreadLocalRandom.current().nextInt(2);
        if(this.isReproduce == false && this.isEat == false && this.isMove == false){
            for (int k=0;k<randomLengthBoar;k++){
                newBoar.add(new Boar());
                Boar.newBoar++;
            }
            this.isReproduce=true;
        }else{
            this.isReproduce = false;
        }
        this.satiety-=10_000;;
        if(this.satiety<0)stepDeath++;
        return newBoar;
    }
    @Override
    public Boar move(Cell[][] cells,int i,int j) {
        if(this.isEat == false && this.isReproduce == false && this.isMove == false){
            int randomStepLength = ThreadLocalRandom.current().nextInt(3);
            int randomWay = ThreadLocalRandom.current().nextInt(4);
            this.isMove=true;
            if(randomWay == 0){
                //west(left)
                int iStepToLeft = j-randomStepLength;
                if(iStepToLeft>=0){
                    this.satiety-=25_000;
                    if(this.satiety<0)stepDeath++;
                    ArrayList<Boar> MoveToOtherBoar = cells[i][iStepToLeft].getBoar();
                    MoveToOtherBoar.add(this);
                    return this;
                }else{
                    Boar.deathBoar++;
                    return this;
                }
            }else if(randomWay == 1){
                //north(up)
                int iStepToUp = i-randomStepLength;
                if(iStepToUp>=0){
                    this.satiety-=25_000;
                    if(this.satiety<0)stepDeath++;

                    ArrayList<Boar> MoveToOtherBoar = cells[iStepToUp][j].getBoar();
                    MoveToOtherBoar.add(this);

                    return this;
                }else{

                    Boar.deathBoar++;
                    return this;
                }
            }else if(randomWay == 2){
                //east(right)
                int iStepToRight = j+randomStepLength;
                if(iStepToRight<cells[0].length){
                    this.satiety-=25_000;
                    if(this.satiety<0)stepDeath++;

                    ArrayList<Boar> MoveToOtherBoar = cells[i][iStepToRight].getBoar();
                    MoveToOtherBoar.add(this);

                    return this;
                }else{

                    Boar.deathBoar++;
                    return this;
                }
            }else if(randomWay == 3){
                //south(down)
                int iStepToDown = i+randomStepLength;
                if(iStepToDown<cells.length){
                    this.satiety-=25_000;
                    if(this.satiety<0)stepDeath++;
                    ArrayList<Boar> MoveToOtherBoar = cells[iStepToDown][j].getBoar();
                    MoveToOtherBoar.add(this);
                    return this;
                }else{

                    Boar.deathBoar++;
                    return this;
                }
            }
        }else{
            this.isMove = false;
        }
        return  null;
    }

    @Override
    public void eat() {

    }

}
