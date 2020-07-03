package com.drobot.task5.enumeration;

public enum Alphabet {
    A(true), B (false), C(false), D(false),
    E(true), F(false), G(false), H(false),
    I(true), J(false), K(false), L(false),
    M(false), N(false), O(true), P(false),
    Q(false), R(false), S(false), T(false),
    U(true), V(false), W(false), X(false),
    Y(true), Z(false);

    private final boolean isVowel;

    Alphabet(boolean isVowel) {
        this.isVowel = isVowel;
    }

    public boolean isVowel() {
        return isVowel;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Alphabet{");

        sb.append("isVowel=").append(isVowel);
        sb.append('}');
        return sb.toString();
    }
}
