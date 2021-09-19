/**
 * Шаблон для отображения имени пользователя с иконками
 * @author Markitanov Vadim
 * @since 05.09.2021
 */
Ext.define('Sencha.classic.view.pages.user.grid.UsernameTpl', {
    extend: 'Ext.XTemplate',
    baseClass: 'user-page-grid-username-tpl',
    cardTpl: [
        '<div class="{[this.baseClass]}">',
        '{username}',

        '<tplif="values.isAdmin"><div class="icons"><i class="fa fa-user-circle red isAdmin"></i></div></tpl>',

        '<tpl if="values.actual">',
            '<div class="icons"><i class="fa fa-check-circle green actualUser"></i></div>',
        '<tpl else>',
            '<div class="icons"><i class="fa fa-exclamation-triangle orange notActualUser"></i></div>',
        '</tpl>',
        '</div>',
    ],
    constructor: function (config) {
        var self = this;
        // Ext.apply(self, config);
        self.callParent(self.cardTpl);
    }
});