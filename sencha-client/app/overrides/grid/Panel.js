/**
 * Переопределение панели сетки
 * @author Markitanov Vadim
 * @since 05.09.2021
 */
Ext.define('Sencha.classic.overrides.grid.Panel', {
    override: 'Ext.grid.Panel',
    emptyText: 'No data.'
});