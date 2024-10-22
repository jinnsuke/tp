package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_DATE_FORMAT;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Birthday;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Remark;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";
    public static final String MESSAGE_INVALID_DOUBLE = "Value is not a valid double.";
    public static final String MESSAGE_INVALID_INT = "Value is not a valid integer.";

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @param oneBasedIndex the index to parse.
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
    }

    /**
     * Parses a {@code String} into a double.
     *
     * @param value the string to parse as a double.
     * @return the parsed double.
     * @throws ParseException if the value is not a valid double.
     */
    public static double parseDouble(String value) throws ParseException {
        requireNonNull(value);
        String trimmedValue = value.trim();
        try {
            return Double.parseDouble(trimmedValue);
        } catch (NumberFormatException e) {
            throw new ParseException(MESSAGE_INVALID_DOUBLE);
        }
    }

    /**
     * Parses a {@code String} into an integer.
     *
     * @param value the string to parse as an integer.
     * @return the parsed integer.
     * @throws ParseException if the value is not a valid integer.
     */
    public static int parseInt(String value) throws ParseException {
        requireNonNull(value);
        String trimmedValue = value.trim();
        try {
            return Integer.parseInt(trimmedValue);
        } catch (NumberFormatException e) {
            throw new ParseException(MESSAGE_INVALID_INT);
        }
    }

    /**
     * Parses a {@code String} into a Name.
     *
     * @param name the string to parse as a Name.
     * @return the parsed Name.
     * @throws ParseException if the name is invalid.
     */
    public static Name parseName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!Name.isValidName(trimmedName)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }
        return new Name(trimmedName);
    }

    /**
     * Parses a {@code String} into a Phone.
     *
     * @param phone the string to parse as a Phone.
     * @return the parsed Phone.
     * @throws ParseException if the phone number is invalid.
     */
    public static Phone parsePhone(String phone) throws ParseException {
        requireNonNull(phone);
        String trimmedPhone = phone.trim();
        if (!Phone.isValidPhone(trimmedPhone)) {
            throw new ParseException(Phone.MESSAGE_CONSTRAINTS);
        }
        return new Phone(trimmedPhone);
    }

    /**
     * Parses a {@code String} into an Address.
     *
     * @param address the string to parse as an Address.
     * @return the parsed Address.
     * @throws ParseException if the address is invalid.
     */
    public static Address parseAddress(String address) throws ParseException {
        requireNonNull(address);
        String trimmedAddress = address.trim();
        if (!Address.isValidAddress(trimmedAddress)) {
            throw new ParseException(Address.MESSAGE_CONSTRAINTS);
        }
        return new Address(trimmedAddress);
    }

    /**
     * Parses a {@code String} into a Remark.
     *
     * @param remark the string to parse as a Remark.
     * @return the parsed Remark.
     */
    public static Remark parseRemark(String remark) {
        requireNonNull(remark);
        String trimmedRemark = remark.trim();
        return new Remark(trimmedRemark);
    }

    /**
     * Parses a {@code String} into a Birthday.
     *
     * @param birthday the string to parse as a Birthday.
     * @return the parsed Birthday.
     * @throws ParseException if the birthday is invalid.
     */
    public static Birthday parseBirthday(String birthday) throws ParseException {
        requireNonNull(birthday);
        String trimmedBirthday = birthday.trim();
        if (!Birthday.isValidBirthday(trimmedBirthday)) {
            throw new ParseException(Birthday.MESSAGE_CONSTRAINTS);
        }
        return new Birthday(trimmedBirthday);
    }

    /**
     * Parses a {@code String} into an Email.
     *
     * @param email the string to parse as an Email.
     * @return the parsed Email.
     * @throws ParseException if the email is invalid.
     */
    public static Email parseEmail(String email) throws ParseException {
        requireNonNull(email);
        String trimmedEmail = email.trim();
        if (!Email.isValidEmail(trimmedEmail)) {
            throw new ParseException(Email.MESSAGE_CONSTRAINTS);
        }
        return new Email(trimmedEmail);
    }

    /**
     * Parses a {@code String} into a Tag.
     *
     * @param tag the string to parse as a Tag.
     * @return the parsed Tag.
     * @throws ParseException if the tag name is invalid.
     */
    public static Tag parseTag(String tag) throws ParseException {
        requireNonNull(tag);
        String trimmedTag = tag.trim();
        if (!Tag.isValidTagName(trimmedTag)) {
            throw new ParseException(Tag.MESSAGE_CONSTRAINTS);
        }
        return new Tag(trimmedTag);
    }

    /**
     * Parses a {@code Collection<String>} into a Set of Tags.
     *
     * @param tags the collection of tag names to parse.
     * @return a Set of parsed Tags.
     * @throws ParseException if any of the tag names are invalid.
     */
    public static Set<Tag> parseTags(Collection<String> tags) throws ParseException {
        requireNonNull(tags);
        final Set<Tag> tagSet = new HashSet<>();
        for (String tagName : tags) {
            tagSet.add(parseTag(tagName));
        }
        return tagSet;
    }

    /**
     * Parses a {@code String} into a LocalDate.
     *
     * @param date the string to parse as a LocalDate.
     * @return the parsed LocalDate.
     * @throws ParseException if the date format is invalid.
     */
    public static LocalDate parseDate(String date) throws ParseException {
        requireNonNull(date);
        String trimmedDate = date.trim();
        try {
            return LocalDate.parse(trimmedDate);
        } catch (DateTimeException e) {
            throw new ParseException(MESSAGE_INVALID_DATE_FORMAT);
        }
    }
}
