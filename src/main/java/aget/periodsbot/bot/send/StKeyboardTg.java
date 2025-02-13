/*
 * MIT License
 *
 * Copyright (c) 2024 Artem Getmanskii
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package aget.periodsbot.bot.send;

import java.util.Objects;
import java.util.function.Supplier;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;

/**
 * Provide Telegram keyboard for message.
 *
 * @since 0.1.0
 */
public final class StKeyboardTg implements Supplier<SendMessage> {
    /**
     * Message text.
     */
    private final String text;

    /**
     * Chat id.
     */
    private final String id;

    /**
     * Keyboard.
     */
    private final ReplyKeyboardMarkup keyboard;

    public StKeyboardTg(
        final String id, final String text, final ReplyKeyboardMarkup keyboard
    ) {
        this.id = id;
        this.text = text;
        this.keyboard = keyboard;
    }

    public SendMessage get() {
        final SendMessage send = new SendMessage(this.id, this.text);
        send.setReplyMarkup(this.keyboard);
        return send;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.text, this.id, this.keyboard);
    }

    @Override
    public boolean equals(final Object object) {
        return this == object
            || object instanceof StKeyboardTg
            && this.text.equals(((StKeyboardTg) object).text)
            && this.id.equals(((StKeyboardTg) object).id)
            && this.keyboard.equals(((StKeyboardTg) object).keyboard);
    }
}
