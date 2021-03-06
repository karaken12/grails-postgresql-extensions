package net.kaleidos.hibernate.criterion.array

import groovy.transform.CompileStatic
import org.hibernate.Criteria
import org.hibernate.HibernateException
import org.hibernate.annotations.common.util.StringHelper
import org.hibernate.criterion.CriteriaQuery
import org.hibernate.criterion.Criterion
import org.hibernate.engine.spi.TypedValue

@CompileStatic
class PgEmptinessExpression implements Criterion {

    private static final long serialVersionUID = 2169068982401072268L

    private final String propertyName
    private final String op

    private static final TypedValue[] NO_VALUES = new TypedValue[0]

    protected PgEmptinessExpression(String propertyName, String op) {
        this.propertyName = propertyName
        this.op = op
    }

    @Override
    String toSqlString(Criteria criteria, CriteriaQuery criteriaQuery) throws HibernateException {
        StringHelper.join(
            " and ",
            StringHelper.suffix(criteriaQuery.findColumns(propertyName, criteria), " ${op} '{}'")
        )
    }

    @Override
    TypedValue[] getTypedValues(Criteria criteria, CriteriaQuery criteriaQuery) throws HibernateException {
        NO_VALUES
    }
}
