package de.moving.turtle.analyze;

import de.moving.turtle.api.AnalyzeResult;
import de.moving.turtle.api.KnownRecord;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.*;

import static java.math.BigDecimal.*;

/**
 * @author Stefan Scheffler(sscheffler@avantgarde-labs.de)
 */
@Component
public class CategoryTotalAnalyzer implements Analyzer<CategoryTotalAnalyzer.CategoryTotalResult> {

    @Override
    public CategoryTotalResult analyze(Collection<KnownRecord> records) {
        final CategoryTotalResult result = new CategoryTotalResult();

        final Map<String, List<KnownRecord>> recordsByCategory = new HashMap<>();
        records.stream()
                .forEach(r -> {
                    if(!recordsByCategory.containsKey(r.category().categoryId())){
                        recordsByCategory.put(r.category().categoryId(), new ArrayList<>());
                    }
                    recordsByCategory.get(r.category().categoryId()).add(r);
                });

        recordsByCategory.forEach((key, value)->{
            final BigDecimal total = value.stream()
                    .map(KnownRecord::value)
                    .reduce(BigDecimal::add)
                    .orElseGet(() -> ZERO);
            result.withTotal(key, total);
        });

        return result;
    }

    public class CategoryTotalResult implements AnalyzeResult{
        public final Map<String, BigDecimal> byCategory = new HashMap<>();

        CategoryTotalResult withTotal(String category, BigDecimal total){
            byCategory.put(category, total);
            return this;
        }
    }
}
