package ranker;
/**
 * Bayesian information criterion (BIC). BIC or Schwarz Criterion is a
 * criterion for model selection among a class of parametric models with
 * different numbers of parameters. Choosing a model to optimize BIC is
 * a form of regularization.
 * 
 * When estimating model parameters using maximum likelihood estimation, it
 * is possible to increase the likelihood by adding additional parameters,
 * which may result in over-fitting. The BIC resolves this problem by
 * introducing a penalty term for the number of parameters in the model.
 * BIC is very closely related to the Akaike information criterion (AIC).
 * However, its penalty for additional parameters is stronger than that of AIC.
 *
 * Given any two estimated models, the model with the larger value of BIC is
 * the one to be preferred.
 * @author Logan Collier
 *
 */
public interface BIC {
    /**
     * Computes Bayesian information criterion (BIC).
     * The formula for the BIC is BIC = L - 0.5 * v * log n where L is the
     * log-likelihood of estimated model, v is the number of free parameters
     * to be estimated in the model, and n is the number of samples.
     *
     * @param L the log-likelihood of estimated model.
     * @param v the number of free parameters to be estimated in the model.
     * @param n the number of samples.
     * @return BIC score.
     */
    static double of(double L, int v, int n) {
        return L - 0.5 * v * Math.log(n);
    }

}
