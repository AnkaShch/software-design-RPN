package tokenizer.state

import tokenizer.tokens.NumberToken
import tokenizer.tokens.Token

class Number : State {
    private var `val`: Int

    constructor(input: String, pos: Int) : super(input, pos) {
        `val` = 0
    }

    private constructor(input: String, pos: Int, `val`: Int) : super(input, pos) {
        this.`val` = `val`
    }

    override fun nextState(tokens: MutableList<Token>): State {
        val checked: State? = checkSpaceAndEnd()
        if (checked != null) {
            tokens.add(NumberToken(`val`))
            return checked
        }
        val c: Char = input[pos]
        if (!Character.isDigit(c)) {
            tokens.add(NumberToken(`val`))
            return Start(input, pos)
        }
        `val` = `val` * 10 + Character.getNumericValue(c)
        return Number(input, pos + 1, `val`)
    }
}