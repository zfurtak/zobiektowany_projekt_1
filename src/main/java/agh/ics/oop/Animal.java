package agh.ics.oop;

public class Animal extends AbstractWorldMapElement {
    private MapDirection orient = MapDirection.NORTH;
    private final AbstractWorldMap map;
    private int energy;
    private Genome genome;


    public Animal(AbstractWorldMap map) {
        this.orient = this.orient.startOrient();
        this.map = map;
        this.energy = map.startEnergy;
        this.genome = new Genome(); ///////// zainteresować sie genomem
    }

    public Animal(AbstractWorldMap map, int energyValue, Animal mum, Animal dad) {
        this.position = mum.position;
        this.map = map;
        this.energy = energyValue;
        this.genome = new Genome(mum, dad);
    }

    public void setPosition(Vector2d pos) {
        this.position = pos;
    }

    public boolean isAnimalDead() {
        return this.energy <= 0;
    }

    public void move() {
        int move = genome.randomGene();
        System.out.println("gen: "+move);
        Vector2d newPosition;
        boolean flag = this.map instanceof ExtendedMap;
        switch (move) {
            case 0 -> {
                newPosition = this.position.add(this.orient.toUnitVector());
                if (flag && !this.map.canMoveTo(newPosition)) {
                    newPosition = teleport(newPosition);
                }
                if(this.map.canMoveTo(newPosition)){
                    notify(this.position, newPosition, this);
                    this.position = newPosition;
                }
            }
            case 1 -> turn(1);
            case 2 -> turn(2);
            case 3 -> turn(3);
            case 4 -> {
                newPosition = this.position.subtract(this.orient.toUnitVector());
                if (flag && !this.map.canMoveTo(newPosition)) {
                    newPosition = teleport(newPosition);
                }
                if(this.map.canMoveTo(newPosition)){
                    notify(this.position, newPosition, this);
                    this.position = newPosition;
                }
            }
            case 5 -> turn(5);
            case 6 -> turn(6);
            case 7 -> turn(7);
        }
    }

    public Vector2d teleport(Vector2d newPosition){
        if(newPosition.x > map.rightUpCorner.x) {
            newPosition = newPosition.subtract(new Vector2d(map.width, 0));
        } else if(newPosition.x < 0){
            newPosition = newPosition.add(new Vector2d(map.width, 0));
        }
        if(newPosition.y > map.rightUpCorner.y){
            newPosition = newPosition.subtract(new Vector2d(0, map.height));
        } else if(newPosition.y < 0){
            newPosition = newPosition.add(new Vector2d(0, map.height));
        }
        return newPosition;
    }

    public void yummy(int x) {
        this.energy += map.plantEnergy / x;
    }

    public void reproduce(Animal partner) {
        this.energy -= this.energy * 0.25;
        partner.energy -= partner.energy * 0.25;
    }

    public void exercise() {
        this.energy -= map.moveEnergy;
    }

    private void turn(int x) {
        for (int i = 0; i < x; i++) {
            this.orient = this.orient.next();
        }
        notify(this.position, this.position, this);
    }

    public int getEnergy() {
        return this.energy;
    }

    public int getGene(int i) {
        return this.genome.genomeArray[i];
    }

    public MapDirection getOrient() {
        return this.orient;
    }

    @Override
    public String getPath() {
        double energy =  this.energy / (double) map.startEnergy;
        if (energy > 0.9)
            return "src/main/resources/10.png";
        else if (energy > 0.8)
            return "src/main/resources/9.png";
        else if (energy > 0.7)
            return "src/main/resources/8.png";
        else if (energy > 0.6)
            return "src/main/resources/7.png";
        else if(energy > 0.5)
            return "src/main/resources/6.png";
        else if(energy > 0.4)
            return "src/main/resources/5.png";
        else if(energy > 0.3)
            return "src/main/resources/4.png";
        else if(energy > 0.2)
            return "src/main/resources/3.png";
        else if(energy > 0.1)
            return "src/main/resources/2.png";
        else
            return "src/main/resources/1.png";

    }
}
