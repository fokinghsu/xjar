package io.xjar;

/**
 * 记录可过滤的解密器
 *
 * @author Payne 646742615@qq.com
 * 2018/11/23 20:38
 */
public abstract class XEntryDecryptor<E> extends XWrappedDecryptor implements XDecryptor, XEntryFilter<E> {
    protected final XEntryFilter<E> filter;
    protected final XNopDecryptor xNopDecryptor = new XNopDecryptor();

    protected XEntryDecryptor(XDecryptor xDecryptor, XEntryFilter<E> filter) {
        super(xDecryptor);
        this.filter = filter;
    }

    @Override
    public boolean filtrate(E entry) {
        return filter == null || filter.filtrate(entry);
    }

    protected static abstract class XEntryDecryptorBuilder<E, T extends XEntryDecryptor<E>, B extends XEntryDecryptorBuilder<E, T, B>> extends XWrappedDecryptorBuilder<T, B> {
        protected XEntryFilter<E> filter;

        public B filter(XEntryFilter<E> filter) {
            this.filter = filter;
            return (B) this;
        }
    }
}
