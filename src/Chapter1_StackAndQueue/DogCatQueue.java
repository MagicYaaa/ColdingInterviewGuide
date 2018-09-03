package Chapter1_StackAndQueue;

import com.sun.org.apache.bcel.internal.generic.INSTANCEOF;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @program: ColdingInterviewGuide
 * @description: 猫狗队列
 * @author: Ya
 * @create: 2018-08-27 22:32
 **/


public class DogCatQueue {
    private Queue<PetEnterQueue> dogQ;
    private Queue<PetEnterQueue> catQ;
    private long count;

    public DogCatQueue() {
        this.dogQ = new LinkedList<>();
        this.catQ = new LinkedList<>();
        this.count = 0;
    }


    public void add(Pet pet) {
        if (pet.getPetType().equals("dog")) {
            this.dogQ.add(new PetEnterQueue(pet, this.count++));
        } else if (pet.getPetType().equals("cat")) {
            this.catQ.add(new PetEnterQueue(pet, this.count++));
        } else {
            throw new RuntimeException("error");
        }
    }

    public Pet pollAll() {
        if (!this.dogQ.isEmpty() && this.catQ.isEmpty()) {
            if (this.dogQ.peek().getCount() < this.catQ.peek().getCount()) {
                return this.dogQ.poll().getPet();
            } else {
                return this.catQ.poll().getPet();

            }
        } else if (this.dogQ.isEmpty()) {
            return this.catQ.poll().getPet();

        } else if (this.catQ.isEmpty()) {
            return this.dogQ.poll().getPet();

        } else {
            throw new RuntimeException("");
        }
    }

    public Dog pollDog() {
        if (!this.dogQ.isEmpty()) {
            return (Dog) this.dogQ.poll().getPet();
        } else {
            throw new RuntimeException("");
        }
    }

    public Cat pollCat() {
        if (!this.dogQ.isEmpty()) {
            return (Cat) this.dogQ.poll().getPet();
        } else {
            throw new RuntimeException("");
        }
    }

    public boolean isEmpty() {
        return dogQ.isEmpty() && catQ.isEmpty();
    }

    public boolean isDogEmpty() {
        return dogQ.isEmpty();
    }

    public boolean isCatEmpty() {
        return catQ.isEmpty();
    }


}

class PetEnterQueue {
    private Pet pet;
    private long count;

    public PetEnterQueue(Pet pet, long count) {
        this.pet = pet;
        this.count = count;
    }

    public Pet getPet() {
        return pet;
    }

    public long getCount() {
        return count;
    }

    public String getEnterPetType() {
        return this.pet.getPetType();
    }
}


class Pet {
    private String type;

    public Pet(String type) {
        this.type = type;
    }

    public String getPetType() {
        return this.type;
    }

}

class Dog extends Pet {
    public Dog() {
        super("dog");
    }
}

class Cat extends Pet {
    public Cat() {
        super("cat");
    }
}

