public final class Cat implements IAggregable<Cat, String>, IDeeplyCloneable<Cat> {

    private final int meows;

    public Cat(int meows) {
        this.meows = Math.max(meows, 0);
    }

    public String meow() {
        if (this.meows == 0) {
            return "I do now meow!";
        }

        return "Meow! ".repeat(Math.max(0, meows));
    }

    @Override
    public String aggregate(String intermediateResult) {
        if (intermediateResult == null) {
            return this.meow();
        }

        return this.meow() + intermediateResult;
    }

    @Override
    public Cat deepClone() {
        return new Cat(this.meows);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Cat cat = (Cat) obj;
        return "Meow! ".repeat(Math.max(0, this.meows)).equals(cat.meow());
    }

    @Override
    public String toString() {
        return "Cat{" +
            "meows=" + this.meows +
            '}';
    }
}
