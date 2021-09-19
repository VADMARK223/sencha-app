/**
 * Шаблон всплывающей подсказки на иконках в ячейках имени пользователя
 * @author Markitanov Vadim
 * @since 05.09.2021
 */
Ext.define('Sencha.classic.view.pages.user.grid.UsernameTooltip', {
    extend: 'Ext.XTemplate',

    baseClass: 'user-page-grid-username-tooltip',
    cardTpl: [
        '<div class="{[this.baseClass]}">',
        '{tooltipText}',
        '</div>', {
            disableFormats: true
        }
    ],
    constructor: function() {
        var self = this;
        self.callParent(self.cardTpl);
    }
});