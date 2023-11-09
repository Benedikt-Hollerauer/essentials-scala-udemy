package Section_5

object PatternMatching extends App
{
    /**
     *  Exercise
     *
     *  simple function uses Pattern Matching
     *  takes an Expr = > human readable form
     *
     *  Sum(Number(2), Number(3)) => 2 + 3
     *  Sum(Number(2), Number(3), Number(4)) => 2 + 3 + 4
     *  Prod(Sum(Number(2), Number(1), Number(3)) = (2 + 1) * 3
     *  Sum(Prod(Number(2), Number(1), Number(3)) = 2 * 1 + 3
     */

    trait Expr
    case class Number(n: Int) extends Expr
    case class Sum(e1: Expr, e2: Expr) extends Expr
    case class Prod(e1: Expr, e2: Expr) extends Expr

    def show(e: Expr): String =
    {
        e.match{
            case Sum(e1, e2) => show(e1) + " + " + show(e2)
            case Number(n) => s"$n"
            case Prod(e1, e2) => {
                def mayBeShowParentheses(exp: Expr) = exp.match{
                    case Number(_) => show(exp)
                    case Prod(_, _) => show(exp)
                    case _ => "(" + show(exp) + ")"
                }

                mayBeShowParentheses(e1) + " * " + mayBeShowParentheses(e2)
            }
        }
    }

    println(show(Sum(Number(2), Number(3))))
    /*println(show(Sum(Number(2), Number(3)), Number(4)))
    println(Prod(Sum(Number(2), Number(1), Number(3))))
    println(Sum(Prod(Number(2), Number(1), Number(3))))*/
}