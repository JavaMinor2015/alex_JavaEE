package JavaEE7.CurrencyConverter.domain.abs;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by alex on 10/29/15.
 */
@Getter
@Setter
@NoArgsConstructor
public class UIEntity {
    private boolean edited;
    private boolean selected;

    public UIEntity(final boolean edited, final boolean selected) {
        this.edited = edited;
        this.selected = selected;
    }
}
